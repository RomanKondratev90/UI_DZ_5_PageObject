package components;

public enum LanguageLevel {
    BEGINNER("Начальный"),
    INTERMEDIATE("Средний"),
    ADVANCED("Продвинутый"),
    NATIVE("Носитель языка");

    private final String displayValue;

    LanguageLevel(String displayName) {
        this.displayValue = displayName;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
