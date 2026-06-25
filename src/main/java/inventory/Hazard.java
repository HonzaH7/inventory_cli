package inventory;

public enum Hazard {
    CORROSIVE("H290"),
    ETCHING("H314"),
    EYEBURNS("H318");

    private final String hSentences;

    Hazard(String h_sentences) {
        this.hSentences = h_sentences;
    }

    public String gethSentences() {
        return hSentences;
    }
}
