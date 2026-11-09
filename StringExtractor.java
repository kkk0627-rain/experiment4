import java.util.ArrayList;
import java.util.List;

public class StringExtractor {

    private enum State {
        OUTSIDE, INSIDE, ESCAPE
    }

    public static List<String> extractStrings(String source) {
        List<String> result = new ArrayList<>();
        State state = State.OUTSIDE;
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (state) {
                case OUTSIDE:
                    if (c == '"') {
                        current.setLength(0);
                        state = State.INSIDE;
                    }
                    break;
                case INSIDE:
                    if (c == '\\') {
                        state = State.ESCAPE;
                    } else if (c == '"') {
                        result.add(current.toString());
                        state = State.OUTSIDE;
                    } else {
                        current.append(c);
                    }
                    break;
                case ESCAPE:
                    current.append(c);
                    state = State.INSIDE;
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String sample = "\"一般学习一门编程语言的时候，基本上第一个程序是输出\\\"Hello World!\\\"\"";
        List<String> strings = extractStrings(sample);
        for (String s : strings) {
            System.out.println(s);
        }
    }
}