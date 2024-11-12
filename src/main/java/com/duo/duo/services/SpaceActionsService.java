package com.duo.duo.services;

import com.duo.duo.model.Space;
import com.duo.duo.model.UserSpace;

public interface SpaceActionsService {
    public void postSpace(Space space);
    public void addUser(UserSpace userSpace);
    public void patchPermission(UserSpace userSpace);
    public void deleteSpace(Space space);
}
