package com.duo.duo.services.implementations;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.duo.duo.dto.QuestionDto.DeleteQuestionDto;
import com.duo.duo.dto.QuestionDto.GetQuestionDto;
import com.duo.duo.dto.QuestionDto.PostQuestionDto;
import com.duo.duo.dto.QuestionDto.PostQuestionResponseDto;
import com.duo.duo.dto.Token;
import com.duo.duo.model.Question;
import com.duo.duo.model.UserSpace;
import com.duo.duo.repositories.QuestionRepository;
import com.duo.duo.repositories.UserSpaceRepository;
import com.duo.duo.services.QuestionActionService;

public class QuestionActionImplementation implements QuestionActionService {

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
    UserSpaceRepository userSpaceRepo;

    @Override
    public PostQuestionResponseDto postQuestion(PostQuestionDto data, Token token) {

        ArrayList<String> messages = new ArrayList<>();
        
        if (!checkFields(data)) {
            messages.add("Preencha todos os campos!");
            return new PostQuestionResponseDto(messages);
        }
        
        Optional<UserSpace> getUserSpace = userSpaceRepo.findById(data.idUserSpace());

        if (getUserSpace.isEmpty()) {
            messages.add("Espaço não encontrado!");
            return new PostQuestionResponseDto(messages);
        }

        UserSpace userSpace = getUserSpace.get();

        if (userSpace.getPermissionLevel() < 2) {
            messages.add("Você não tem permissão para fazer uma pergunta!");
            return new PostQuestionResponseDto(messages);
        }
        
        Question question = new Question();

        question.setDescription(data.description());
        question.setUserSpace(userSpace);

        questionRepo.save(question);

        messages.add("Pergunta feita com sucesso!");

        return new PostQuestionResponseDto(messages);
    }

    @Override
    public GetQuestionDto getQuestion(Long id) {
        
        Optional<Question> getQuestion = questionRepo.findById(id);
        ArrayList<String> messages =  new ArrayList<>();
        
        if (getQuestion.isEmpty()) {
            messages.add("A pergunta não foi encontrada!");
            return new GetQuestionDto(null, null, messages);
        }
        
        Question question = getQuestion.get();

        return new GetQuestionDto(question.getDescription(), question.getUserSpace().getUser().getId(), messages);
    }

    @Override
    public DeleteQuestionDto deleteQuestion(Long idQuestion) {

        Optional<Question> getQuestion = questionRepo.findById(idQuestion);
        Question question = getQuestion.get();

        UserSpace userSpace = question.getUserSpace();

        if (userSpace.getPermissionLevel() < 3) {
            return new DeleteQuestionDto(0, "Você não tem permissão para deletar essa pergunta!");
        }

        questionRepo.delete(question);

        return new DeleteQuestionDto(1, "Pergunta deletada com sucesso!");
    }

    public Boolean checkFields(PostQuestionDto data) {

        if (data.description() == null || data.idUserSpace() == null || 
            data.description().equals("") || data.idUserSpace() == 0) {
            
            return false;
        }

        return true;
    }

    @Override
    public ArrayList<Question> getAllQuestions(Long spaceId, Integer page, Integer limit) {
        var results = questionRepo.findByUserSpaceId(spaceId, PageRequest.of(page, limit)); 

        return new ArrayList<>(results);

    }
    
}
