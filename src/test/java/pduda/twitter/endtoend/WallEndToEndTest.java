package pduda.twitter.endtoend;

import org.junit.Test;

import static pduda.twitter.util.ObjectMother.someDay;

public class WallEndToEndTest extends EndToEndTest {

    @Test(timeout = 1000)
    public void wallForASocialNetworkerWithNoFollowees() throws Exception {
        enter("Charlie -> I'm in New York today! Anyone want to have a coffee?", whenTimeIs(someDay().atTime(9, 59, 45)));

        enter("Charlie wall", whenTimeIs(someDay().atTime(10, 0)));
        assertOutputLines(
                "Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)"
        );

        enter("quit");
    }

    @Test(timeout = 1000)
    public void wallForASocialNetworkerWhoFollowsSomebody() throws Exception {
        enter("Alice -> I love the weather today", whenTimeIs(someDay().atTime(9, 55)));
        enter("Bob -> Damn! We lost!", whenTimeIs(someDay().atTime(9, 58)));
        enter("Bob -> Good game though.", whenTimeIs(someDay().atTime(9, 59)));
        enter("Charlie -> I'm in New York today! Anyone want to have a coffee?", whenTimeIs(someDay().atTime(9, 59, 45)));

        enter("Charlie follows Alice");
        enter("Charlie follows Bob");

        enter("Charlie wall", whenTimeIs(someDay().atTime(10, 0)));
        assertOutputLines(
                "Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)",
                "Bob - Good game though. (1 minute ago)",
                "Bob - Damn! We lost! (2 minutes ago)",
                "Alice - I love the weather today (5 minutes ago)"
        );

        enter("quit");
    }

}
