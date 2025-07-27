public class TestJava21 {
    public static void main(String[] args) {
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Java 21 is working correctly!");
        
        // Java 21の新機能を使用したテスト
        var text = """
            This is a text block,
            which is available since Java 13
            and works great with Java 21!
            """;
        System.out.println(text);
    }
}
