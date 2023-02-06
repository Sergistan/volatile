import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final AtomicInteger length3 = new AtomicInteger();
    private static final AtomicInteger length4 = new AtomicInteger();
    private static final AtomicInteger length5 = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
            System.out.println(texts[i]);
        }
        System.out.println();

        List<Thread> threadList = new ArrayList<>();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isPalindrome(texts[i])) {
                    if (texts[i].length() == 3) {
                        length3.getAndIncrement();
                        System.out.println(texts[i]);
                    } else if (texts[i].length() == 4) {
                        length4.getAndIncrement();
                        System.out.println(texts[i]);
                    } else if (texts[i].length() == 5) {
                        length5.getAndIncrement();
                        System.out.println(texts[i]);
                    }
                }
            }
        });
        thread1.start();
        threadList.add(thread1);

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (allCharactersSame(texts[i])) {
                    if (texts[i].length() == 3) {
                        length3.getAndIncrement();
                        System.out.println(texts[i]);
                    } else if (texts[i].length() == 4) {
                        length4.getAndIncrement();
                        System.out.println(texts[i]);
                    } else if (texts[i].length() == 5) {
                        length5.getAndIncrement();
                        System.out.println(texts[i]);
                    }
                }
            }
        });
        thread2.start();
        threadList.add(thread2);

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isInOrder(texts[i])) {
                    if (texts[i].length() == 3) {
                        length3.getAndIncrement();
                        System.out.println(texts[i]);
                    } else if (texts[i].length() == 4) {
                        length4.getAndIncrement();
                        System.out.println(texts[i]);
                    } else if (texts[i].length() == 5) {
                        length5.getAndIncrement();
                        System.out.println(texts[i]);
                    }
                }
            }
        });
        thread3.start();
        threadList.add(thread3);

        for (Thread thread : threadList) {
            thread.join();
        }

        System.out.println("Красивых слов с длиной 3: " + length3 + " шт");
        System.out.println("Красивых слов с длиной 4: " + length4 + " шт");
        System.out.println("Красивых слов с длиной 5: " + length5 + " шт");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String str) {
        StringBuilder rev = new StringBuilder();
        boolean ans = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            rev.append(str.charAt(i));
        }
        if (str.equals(rev.toString())) {
            ans = true;
        }
        return ans;
    }

    static boolean allCharactersSame(String str) {
        int n = str.length();
        for (int i = 1; i < n; i++) {
            if (str.charAt(i) != str.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isInOrder(String str) {
        char[] a = str.toCharArray();
        Arrays.sort(a);
        return Arrays.equals(a, str.toCharArray());
    }
}
