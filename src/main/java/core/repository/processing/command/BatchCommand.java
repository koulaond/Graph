package core.repository.processing.command;

import java.util.HashSet;
import java.util.Set;

public class BatchCommand implements Command {

  private Set<Command> commands;

  private BatchCommand() {
    this.commands = new HashSet<>();
  }

  public Set<Command> getCommands() {
    return commands;
  }

  public static BatchCommandBuilder builder() {
     return new BatchCommandBuilder();
  }

  @Override
  public ActionType getActionType() {
    return ActionType.BATCH;
  }

  public static class BatchCommandBuilder {
    private BatchCommand command;

    private BatchCommandBuilder() {
      this.command = new BatchCommand();
    }

    public BatchCommandBuilder command(Command command){
      this.command.getCommands().add(command);
      return this;
    }

    public BatchCommand build() {
      return command;
    }
  }

}
