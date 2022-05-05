package com.hitema.modeles;

public class Teacher extends Person{
    private String module;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Teacher{");
        sb.append("module='").append(module).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
