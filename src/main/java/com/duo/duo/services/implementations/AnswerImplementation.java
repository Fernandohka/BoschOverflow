package com.duo.duo.services.implementations;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.duo.duo.dto.AnswerDto.GetAnswersResponse;
import com.duo.duo.dto.AnswerDto.NewAnswerDto;
import com.duo.duo.dto.AnswerDto.NewAnswerResponseDto;
import com.duo.duo.model.Answer;
import com.duo.duo.model.Question;
import com.duo.duo.model.User;
import com.duo.duo.repositories.AnswerRepository;
import com.duo.duo.repositories.QuestionRepository;
import com.duo.duo.repositories.UserRepository;
import com.duo.duo.repositories.UserSpaceRepository;
import com.duo.duo.services.AnswerService;
import com.duo.duo.services.SpaceActionsService;

public class AnswerImplementation implements AnswerService {

    @Autowired
    SpaceActionsService spaceService;

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
    UserSpaceRepository userSpaceRepo;

    @Autowired
    AnswerRepository answerRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public NewAnswerResponseDto postAnswer(NewAnswerDto data, Long idUser) {

        Optional<Question> getQuestion = questionRepo.findById(data.questionId());
        Optional<User> getUser = userRepo.findById(idUser);

        User user = getUser.get();

        if (getQuestion.isEmpty())
            return new NewAnswerResponseDto(0, "A pergunta não foi encontrada!");

        Question question = getQuestion.get();

        Integer permission = spaceService.checkUserPermission(idUser, question.getUserSpace().getId());

        if (permission < 2)
            return new NewAnswerResponseDto(1, "Você não tem permissão para fazer uma pergunta!");
        
        Answer newAnswer = new Answer();

        newAnswer.setDescription(data.description());
        newAnswer.setQuestion(question);
        newAnswer.setUser(user);

        answerRepo.save(newAnswer);

        return new NewAnswerResponseDto(2, "Pergunta postada com sucesso!");
    }

    @Override
    public GetAnswersResponse getAnswers(Long questionId) {

        ArrayList<Answer> answersList = answerRepo.findByQuestionId(questionId);

        if (answersList.isEmpty())
            return new GetAnswersResponse( null, "Não foram encontradas respostas para essa pergunta!");

        return new GetAnswersResponse(answersList, "Aqui está a lista de respostas dessa pergunta");
    }
}
