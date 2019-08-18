package fish.eyebrow.puddingaccount;

import com.google.inject.Guice;

public class Application {

    public static void main(String[] args) {
        Guice.createInjector(new PuddingAccountModule());
    }
}
