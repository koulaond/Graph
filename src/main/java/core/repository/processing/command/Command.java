package core.repository.processing.command;

import core.repository.processing.ActionType;

public interface Command {
  ActionType getActionType();

}
