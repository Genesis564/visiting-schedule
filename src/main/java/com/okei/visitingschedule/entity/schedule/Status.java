package com.okei.visitingschedule.entity.schedule;

public enum Status {
    WAITING_TO_CONFIRM("В ожидании подтверждения"),
    WAITING_TO_CONFIRM_EVENT("В ожидании подтверждения выполнения мероприятия"),
    PLANNED("Запланированно"),
    SUMMING_UP("Подведение итогов"),
    CONFIRMED("Подтверждено"),
    OVERDUE("Просрочено");

    private String statusName;
    Status(String name) {
        this.statusName = name;
    }

    public String getName() {
        return statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }


}
