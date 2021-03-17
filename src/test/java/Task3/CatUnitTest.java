package Task3;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CatUnitTest {

    static final String CAT_BARON = "Baron";
    static final String CAT_BONIFACY = "Bonifacy";
    static final String CAT_MRUCZEK = "Mruczek";
    static final String CAT_LUNA = "Luna";
    static final String CAT_OTHER_CAT = "OtherCat";

    static final Cat CAT_BARON_4 = new Cat(4, CAT_BARON);
    static final Cat CAT_BARON_5 = new Cat(5, CAT_BARON);
    static final Cat CAT_BARON_18 = new Cat(18, CAT_BARON);
    static final Cat CAT_BONIFACY_2 = new Cat(2, CAT_BONIFACY);
    static final Cat CAT_MRUCZEK_9 = new Cat(9, CAT_MRUCZEK);
    static final Cat CAT_LUNA_5 = new Cat(5, CAT_LUNA);

    private static Stream<Arguments> getCatsForAddition() {
        return Stream.of(
                Arguments.of(CAT_BARON, 5, 4),
                Arguments.of(CAT_BARON, null, 4),
                Arguments.of(null, -1, 5),
                Arguments.of(CAT_OTHER_CAT, 9, 5)
        );
    }

    private static Stream<Arguments> getCatsForRemoval() {
        return Stream.of(
                Arguments.of(CAT_OTHER_CAT, 21, 4),
                Arguments.of(null, 0, 4),
                Arguments.of(CAT_LUNA, null, 3),
                Arguments.of(CAT_LUNA, 5, 3)
        );
    }

    @Nested
    @DisplayName("equality of objects tested using Set of objects")
    class SetCases {
        private Set<Cat> cats;

        @BeforeEach
        void prepare() {
            cats = new HashSet<>();
            cats.add(CAT_MRUCZEK_9);
            cats.add(CAT_BARON_4);
            cats.add(CAT_BONIFACY_2);
            cats.add(CAT_LUNA_5);
        }

        @ParameterizedTest
        @MethodSource("Task3.CatUnitTest#getCatsForAddition")
        @DisplayName("should correctly handle addition to a set of Cats with given parameters:")
        void testAddition(String name, Integer lives, int expectedSize) {
            //given
            Cat cat = new Cat(lives, name);

            //when
            cats.add(cat);

            //then
            assertEquals(expectedSize, cats.size());
        }

        @ParameterizedTest
        @MethodSource("Task3.CatUnitTest#getCatsForRemoval")
        @DisplayName("should correctly handle removal of Cats with given parameters:")
        void testRemoval(String name, Integer lives, int expectedSize) {
            //given
            Cat cat = new Cat(lives, name);

            //when
            cats.remove(cat);

            //then
            assertEquals(expectedSize, cats.size());
        }

    }

    @Nested
    @DisplayName("sorting of objects tested using Comparator")
    class ComparatorCases {

    private List<Cat> cats;
    private final CatComparator catComparator = new CatComparator();

        @BeforeEach
        void prepare() {
            cats = new ArrayList<>();
            cats.add(CAT_MRUCZEK_9);
            cats.add(CAT_BARON_4);
            cats.add(CAT_BONIFACY_2);
            cats.add(CAT_LUNA_5);
            cats.add(CAT_BARON_5);
            cats.add(new Cat(null, CAT_BONIFACY));
        }

        @Test
        @DisplayName("should correctly handle sorting a list of Cats")
        void whenSortingOriginalList_checkWithArrays() {
            //given
            Integer[] expectedLives = new Integer[] {Integer.MAX_VALUE, 9, 5, 5, 4, 2};
            String[] expectedNames = new String[] {CAT_BONIFACY, CAT_MRUCZEK, CAT_LUNA, CAT_BARON, CAT_BARON, CAT_BONIFACY};

            //when
            Collections.sort(cats, catComparator);
            Integer[] actualLives = cats.stream().map(cat -> cat.getLives()).toArray(Integer[]::new);
            String[] actualNames = cats.stream().map(cat -> cat.getName()).toArray(String[]::new);

            //then
            assertTrue(Arrays.equals(expectedLives, actualLives));
            assertTrue(Arrays.equals(expectedNames, actualNames));
        }

        @Test
        @DisplayName("should correctly handle sorting a list of Cats")
        void whenSortingOriginalList_checkWithLists() {
            //given
            List<Integer> expectedLives = List.of(Integer.MAX_VALUE, 9, 5, 5, 4, 2);
            List<String> expectedNames = List.of(CAT_BONIFACY, CAT_MRUCZEK, CAT_LUNA, CAT_BARON, CAT_BARON, CAT_BONIFACY);

            //when
            Collections.sort(cats, catComparator);
            List<Integer> actualLives = cats.stream().map(cat -> cat.getLives()).collect(Collectors.toList());
            List<String> actualNames = cats.stream().map(cat -> cat.getName()).collect(Collectors.toList());

            //then
            assertEquals(expectedLives, actualLives);
            assertEquals(expectedNames, actualNames);
        }
    }

    @Nested
    @DisplayName("filtering of objects tested using Predicate")
    class PredicateCases {

        private List<Cat> cats;
        private final Predicate<Cat> namePredicate = c -> c.getName() == CAT_BARON;
        private final Predicate<Cat> livesPredicate = c -> c.getLives() > 3 && c.getLives() < 7;

        @BeforeEach
        void prepare() {
            cats = new ArrayList<>();
            cats.add(CAT_MRUCZEK_9);
            cats.add(CAT_BARON_4);
            cats.add(CAT_BONIFACY_2);
            cats.add(CAT_LUNA_5);
            cats.add(CAT_BARON_5);
            cats.add(CAT_BARON_18);
        }

        @Nested
        @DisplayName("should")
        class HappyCases {

            @Test
            @DisplayName("contain 4 Cat objects after filtering by name OR lives Predicate")
            void whenFilteringByNameOrLivesPredicate() {
                //when
                List<Cat> filteredCats = cats.stream()
                        .filter(namePredicate.or(livesPredicate))
                        .collect(Collectors.toList());

                //then
                assertThat(filteredCats).hasSize(4)
                                        .extracting(Cat::getName)
                                            .doesNotContain(CAT_BONIFACY, CAT_MRUCZEK);
            }

            @Test
            @DisplayName("contain 2 Cat objects after filtering by name AND lives Predicate")
            void whenFilteringByNameAndLivesPredicate() {
                //when
                List<Cat> filteredCats = cats.stream()
                        .filter(namePredicate.and(livesPredicate))
                        .collect(Collectors.toList());

                //then
                assertThat(filteredCats).hasSize(2)
                                        .extracting("lives", "name")
                                        .contains(tuple(CAT_BARON_4.getLives(), CAT_BARON),
                                                  tuple(CAT_BARON_5.getLives(), CAT_BARON));
            }

            @Test
            @DisplayName("contain 1 Cat object after filtering by name AND negated lives Predicate")
            void whenFilteringByNameAndNegateLivesPredicate() {
                //when
                List<Cat> filteredCats = cats.stream()
                        .filter(namePredicate.and(livesPredicate.negate()))
                        .collect(Collectors.toList());

                //then
                assertThat(filteredCats).hasSize(1)
                                        .extracting("lives", "name")
                                        .contains(tuple(CAT_BARON_18.getLives(), CAT_BARON));
            }
        }
    }

}
