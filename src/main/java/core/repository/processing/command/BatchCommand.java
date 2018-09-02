package core.repository.processing.command;

import java.util.Set;

public class BatchCommand implements Command {

  private Set<Command> commands;

  public BatchCommand(Set<Command> commands) {
    this.commands = commands;
  }

  public Set<Command> getCommands() {
    return commands;
  }

  @Override
  public ActionType getActionType() {
    return null;
  }


}
