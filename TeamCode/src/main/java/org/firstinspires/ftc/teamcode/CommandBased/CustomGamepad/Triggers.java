package org.firstinspires.ftc.teamcode.CommandBased.CustomGamepad;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Triggers {
    private static final List<Trigger> allTriggers = new ArrayList<>();

    public static Trigger on(BooleanSupplier condition) {
        Trigger trigger = new Trigger(condition);
        allTriggers.add(trigger);
        return trigger;
    }

    public static void updateAll() {
        for (Trigger trigger : allTriggers) {
            trigger.update();
        }
    }
}
