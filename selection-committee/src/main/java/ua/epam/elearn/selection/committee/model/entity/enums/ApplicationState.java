package ua.epam.elearn.selection.committee.model.entity.enums;

public enum ApplicationState {

    REGISTERED(1),
    CANCELED(2),
    REJECTED(3),
    ACCEPTED_FOR_CONTRACT(4),
    ACCEPTED_FOR_BUDGET(5);

    private final long id;

    ApplicationState(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public static ApplicationState getValue(long id) {
        ApplicationState ret = null;
        for (ApplicationState type : ApplicationState.values()) {
            if (type.getId() == id)
                ret = type;
        }
        return ret;
    }

}
