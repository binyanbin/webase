package com.bin.webase.domain.command.model.command;

public abstract class IdCommand extends BaseCommand {
    private Long id;

    public IdCommand(Long id) {
        super();
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String getInfo(){
        return "id:"+id.toString();
    }
}
