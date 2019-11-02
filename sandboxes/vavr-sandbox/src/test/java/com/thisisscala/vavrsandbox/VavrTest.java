package com.thisisscala.vavrsandbox;

import io.vavr.Function1;
import io.vavr.Predicates;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

public class VavrTest {

    @Test
    public void example1() {
        // Function1, Function2, ..., Function8
        // Tuple4, TupleN
        List.of(1, 2, 3);
        List(1, 2, 3);
    }

    @Test
    public void example2() {
        Option("42") // `Option` is an iterable
                .map(s -> s.toUpperCase())
                .filter(s -> s.length() > 0);

        List(Option(42), Option(54)) // `List` is an iterable as well
                .filter(o -> o.isDefined())
                .map(o -> o.get()) // `.get()` is never intended to be used
                .collect(Collectors.toList());
        // Syntactic sugar to convert a `NullPointerException` to a `NoSuchElementException`
        val l = List(Option(42), Option(54))
                .flatMap(Function1.identity());// Collapse the list easily
        System.out.println(l);
    }

    @Test
    public void example3() {
        Try(() -> new URI(""))
                .recoverWith(URISyntaxException.class, Try(() -> new URI("")))
                .map(uri -> uri.toString())
                .filter(i -> true)
                .getOrElse("default");
    }

    @Test
    public void example4() {
        Supplier<Integer> sup = () -> { // Lazy-like
            System.out.println("Computing...");
            return 42;
        };
        sup.get();
        sup.get(); // Problem: `Computing...` is printed out every time b/c `sup` is always lazy initialized
        sup.get();

        val lazy = Lazy(() -> {
            System.out.println("Computing...");
            return 42;
        }).map(i -> i + 1); // Recomputed every time you require the value
        lazy.get();
        lazy.get(); // `Computing` is printed out only once
        lazy.get();
    }

    @Test
    public void example6() {
        // The collection API has been totally redesigned in vavr
        // You always get new instances b/c of immutability

        // Persistent collections addresses the problem of constructing too many instances
        // Unrelated to databases (reuse everything as much as possible)
        // -> This is how it is implemented in vavr
        val l = List(1, 2, 3);
        l.
                drop(1)
                .tail();
    }

    @Test
    public void example7() {
        val l = List(1, 2, 3);
        val ll = l.crossProduct(3); // Generate all combinations
        System.out.println(ll.collect(Collectors.toList()));
        System.out.println(l.zip(l.map(i -> i - l.length() - 1)));

        val lost = List(4, 8, 15, 16, 23, 42);
        System.out.println(lost.zipWithIndex());
        System.out.println(lost.zipWithIndex().map(t -> t._2).sum());
    }

    @Test
    public void example8() {
        List(1, 2, 3)
                .collect(Collectors.toList()); // Java 8 collectors
    }

    @Test
    public void example9() {
        // In Java 8, streams are fancy iterators
        // In vavr, streams are a reusable lazy data structure
        val s = Stream.iterate(0, i -> i + 1);
        System.out.println(s); // Prints `Stream(0, ?)`

        val ss = Stream.iterate(0, i -> i + 1)
                .take(10);
        System.out.println(s); // Still prints `Stream(0, ?)`

        Stream.iterate(0, i -> i + 1)
                .take(10)
                .forEach(System.out::print);
    }

    @Test
    public void example10() {
        // Spring Data supports vavr collections out of the box
        List(1, 2)
                .toJavaList(); // Linear time when doing this conversion
        List(1, 2)
                .asJava(); // Constant time when doing this conversion
    }

    @Test
    public void example11() {
        val t = Tuple("12", 12, 1.2f);
        System.out.println(t._1);
        System.out.println(t.arity());
        System.out.println(t.toSeq().map(Object::toString).collect(Collectors.joining(", ")));
        val concat = t.apply((s, integer, aFloat) -> s + integer + aFloat);
        System.out.println(concat);
    }

    @Test
    public void example12() {
        // Checked exceptions and lambdas are painful
        // Functional interfaces (i.e., interfaces that represent functions) do not have `throws`
        List("")
                .map(unchecked(s -> new URI(s)));
        // /Sneaky throw/: throw exceptions when the compiler is not looking at you
    }

    @Test
    public void example13() {
        val l = List(Some(12), Some(24), None(), Some(21));
        val rs = l.map(o -> Match(o).of(
                Case(Some($()), "defined"),
                Case(None(), "empty")
        )).collect(Collectors.toList());
        System.out.println(rs);

        val a = "32";
        Match(a).of(
                Case($(instanceOf(String.class)), "string"),
                Case($(instanceOf(Integer.class)), "int"));
    }
}
