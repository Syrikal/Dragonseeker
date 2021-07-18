package syric.dragonseeker;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class DragonseekerConfig {

    public static class Common {
        //Basic Dragonseeker
        public final ForgeConfigSpec.ConfigValue<Integer> basic_optimalDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> basic_maxDistance;
        public final ForgeConfigSpec.ConfigValue<Double> basic_minPingChance;
        public final ForgeConfigSpec.ConfigValue<Double> basic_maxPingChance;

        public final ForgeConfigSpec.ConfigValue<Integer> basic_pingCapRadius;
        public final ForgeConfigSpec.ConfigValue<Double> basic_sigPower;
        public final ForgeConfigSpec.ConfigValue<Double> basic_minVol;
        public final ForgeConfigSpec.ConfigValue<Double> basic_maxVol;
        public final ForgeConfigSpec.ConfigValue<Double> basic_minPitch;
        public final ForgeConfigSpec.ConfigValue<Double> basic_maxPitch;

        public final ForgeConfigSpec.ConfigValue<Boolean> basic_detectsCorpses;
        public final ForgeConfigSpec.ConfigValue<Boolean> basic_detectsTame;
        public final ForgeConfigSpec.ConfigValue<Integer> basic_durability;


        //Epic Dragonseeker
        public final ForgeConfigSpec.ConfigValue<Integer> epic_optimalDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> epic_maxDistance;
        public final ForgeConfigSpec.ConfigValue<Double> epic_minPingChance;
        public final ForgeConfigSpec.ConfigValue<Double> epic_maxPingChance;

        public final ForgeConfigSpec.ConfigValue<Integer> epic_pingCapRadius;
        public final ForgeConfigSpec.ConfigValue<Double> epic_sigPower;
        public final ForgeConfigSpec.ConfigValue<Double> epic_minVol;
        public final ForgeConfigSpec.ConfigValue<Double> epic_maxVol;
        public final ForgeConfigSpec.ConfigValue<Double> epic_minPitch;
        public final ForgeConfigSpec.ConfigValue<Double> epic_maxPitch;

        public final ForgeConfigSpec.ConfigValue<Boolean> epic_detectsCorpses;
        public final ForgeConfigSpec.ConfigValue<Boolean> epic_detectsTame;
        public final ForgeConfigSpec.ConfigValue<Integer> epic_durability;


        //Legendary Dragonseeker
        public final ForgeConfigSpec.ConfigValue<Integer> legendary_optimalDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> legendary_maxDistance;
        public final ForgeConfigSpec.ConfigValue<Double> legendary_minPingChance;
        public final ForgeConfigSpec.ConfigValue<Double> legendary_maxPingChance;

        public final ForgeConfigSpec.ConfigValue<Integer> legendary_pingCapRadius;
        public final ForgeConfigSpec.ConfigValue<Double> legendary_sigPower;
        public final ForgeConfigSpec.ConfigValue<Double> legendary_minVol;
        public final ForgeConfigSpec.ConfigValue<Double> legendary_maxVol;
        public final ForgeConfigSpec.ConfigValue<Double> legendary_minPitch;
        public final ForgeConfigSpec.ConfigValue<Double> legendary_maxPitch;

        public final ForgeConfigSpec.ConfigValue<Boolean> legendary_detectsCorpses;
        public final ForgeConfigSpec.ConfigValue<Boolean> legendary_detectsTame;
        public final ForgeConfigSpec.ConfigValue<Integer> legendary_durability;


        //Mythic Dragonseeker
        public final ForgeConfigSpec.ConfigValue<Integer> mythic_optimalDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> mythic_maxDistance;
        public final ForgeConfigSpec.ConfigValue<Double> mythic_minPingChance;
        public final ForgeConfigSpec.ConfigValue<Double> mythic_maxPingChance;

        public final ForgeConfigSpec.ConfigValue<Integer> mythic_pingCapRadius;
        public final ForgeConfigSpec.ConfigValue<Double> mythic_sigPower;
        public final ForgeConfigSpec.ConfigValue<Double> mythic_minVol;
        public final ForgeConfigSpec.ConfigValue<Double> mythic_maxVol;
        public final ForgeConfigSpec.ConfigValue<Double> mythic_minPitch;
        public final ForgeConfigSpec.ConfigValue<Double> mythic_maxPitch;

        public final ForgeConfigSpec.ConfigValue<Boolean> mythic_detectsCorpses;
        public final ForgeConfigSpec.ConfigValue<Boolean> mythic_detectsTame;
        public final ForgeConfigSpec.ConfigValue<Integer> mythic_durability;



        public Common(ForgeConfigSpec.Builder builder){

            builder.comment("Ping Chance Stats").push("ping_chance_stats");

            basic_optimalDistance = builder.comment("Inside this distance, getting closer to the dragon will not increase the ping chance.")
                    .comment("Defaults: 100, 150, 200, 500")
                    .defineInRange("basic", 100, 1, 500);
            epic_optimalDistance = builder.defineInRange("epic", 150, 1, 500);
            legendary_optimalDistance = builder.defineInRange("legendary", 200, 1, 500);
            mythic_optimalDistance = builder.defineInRange("godly", 500, 1, 500);


            basic_maxDistance = builder.comment("Maximum distance at which a dragon will be detected. Should be larger than the optimalDistance, but not larger than your render distance.")
                    .comment("Defaults: 200, 225, 250, 500")
                    .defineInRange("basic", 200, 1, 500);
            epic_maxDistance = builder.defineInRange("epic", 225, 1, 500);
            legendary_maxDistance = builder.defineInRange("legendary", 250, 1, 500);
            mythic_maxDistance = builder.defineInRange("godly", 500, 1, 500);


            basic_minPingChance = builder.comment("Minimum chance of a ping. This is the chance of a positive ping when no dragons are detected, i.e. the false positive rate.")
                    .comment("Defaults: 0.12, 0.08, 0.04, 0")
                    .defineInRange("basic", 0.12, 0, 1);
            epic_minPingChance = builder.defineInRange("epic", 0.08, 0, 1);
            legendary_minPingChance = builder.defineInRange("legendary", 0.04, 0, 1);
            mythic_minPingChance = builder.defineInRange("godly", 0D, 0, 1);


            basic_maxPingChance = builder.comment("Maximum chance of a ping. This is the chance of a positive ping when there is a dragon within the optimalDistance.")
                    .comment("Defaults: 0.8, 0.9, 0.95, 1")
                    .defineInRange("basic", 0.8, 0, 1);
            epic_maxPingChance = builder.defineInRange("epic", 0.9, 0, 1);
            legendary_maxPingChance = builder.defineInRange("legendary", 0.95, 0, 1);
            mythic_maxPingChance = builder.defineInRange("godly", 1D, 0, 1);



            builder.pop();
            builder.comment("Ping Property Stats").push("ping_property_stats");

            basic_pingCapRadius = builder.comment("Inside this radius, ping properties will not change. The smaller it is, the easier it will be to narrow down a dragon's location.")
                    .comment("Defaults: 125, 125, 100, 0")
                    .defineInRange("basic", 125, 1, 500);
            epic_pingCapRadius = builder.defineInRange("epic", 125, 1, 500);
            legendary_pingCapRadius = builder.defineInRange("legendary", 100, 1, 500);
            mythic_pingCapRadius = builder.defineInRange("godly", 0, 1, 500);


            basic_sigPower = builder.comment("Mathematical property governing the relationship between distance and ping properties. The higher this is, the easier it will be to narrow down a dragon's location.")
                    .comment("Defaults: 1.5, 1.5, 2.5, 3.5")
                    .defineInRange("basic", 1.5, 1, 5);
            epic_sigPower = builder.defineInRange("epic", 1.5, 1, 5);
            legendary_sigPower = builder.defineInRange("legendary", 2.5, 1, 5);
            mythic_sigPower = builder.defineInRange("godly", 3.5, 1, 5);


            basic_minVol = builder.comment("Minimum volume of a ping, for when there are no dragons in range.")
                    .comment("Defaults: 0.05")
                    .defineInRange("basic", 0.05, 0, 1);
            epic_minVol = builder.defineInRange("epic", 0.05, 0, 1);
            legendary_minVol = builder.defineInRange("legendary", 0.05, 0, 1);
            mythic_minVol = builder.defineInRange("godly", 0.05, 0, 1);


            basic_maxVol = builder.comment("Maximum volume of a ping, for when you're inside the ping cap radius.")
                    .comment("Defaults: 0.05, 0.4, 0.7, 1")
                    .defineInRange("basic", 0.05, 0, 1);
            epic_maxVol = builder.defineInRange("epic", 0.4, 0, 1);
            legendary_maxVol = builder.defineInRange("legendary", 0.7, 0, 1);
            mythic_maxVol = builder.defineInRange("godly", 1D, 0, 1);


            basic_minPitch = builder.comment("Pitch of a negative result, i.e. no dragon detected.")
                    .comment("Defaults: 0.5")
                    .defineInRange("basic", 0.5, 0, 1);
            epic_minPitch = builder.defineInRange("epic", 0.5, 0, 1);
            legendary_minPitch = builder.defineInRange("legendary", 0.5, 0, 1);
            mythic_minPitch = builder.defineInRange("godly", 0.5, 0, 1);


            basic_maxPitch = builder.comment("Pitch of a positive result, i.e. there is a dragon detected.")
                    .comment("Defaults: 0.8, 0.8, 0.8, 1")
                    .defineInRange("basic", 0.8, 0, 1);
            epic_maxPitch = builder.defineInRange("epic", 0.8, 0, 1);
            legendary_maxPitch = builder.defineInRange("legendary", 0.8, 0, 1);
            mythic_maxPitch = builder.defineInRange("godly", 1D, 0, 1);



            builder.pop();
            builder.comment("Other Stats").push("other_stats");

            basic_detectsCorpses = builder.comment("Whether or not the dragonseeker detects dead dragons.")
                    .comment("Defaults: true, false, false, false")
                    .define("basic", true);
            epic_detectsCorpses = builder.define("epic", false);
            legendary_detectsCorpses = builder.define("legendary", false);
            mythic_detectsCorpses = builder.define("godly", false);


            basic_detectsTame = builder.comment("Whether or not the dragonseeker detects tame dragons.")
                    .comment("Defaults: true, true, false, false")
                    .define("basic", true);
            epic_detectsTame = builder.define("epic", true);
            legendary_detectsTame = builder.define("legendary", false);
            mythic_detectsTame = builder.define("godly", false);


            basic_durability = builder.comment("Durability of the item. Set to -1 to make it unbreakable.")
                    .comment("Defaults: 128, 256, 512, -1")
                    .defineInRange("basic", 128, -1, 2048);
            epic_durability = builder.defineInRange("epic", 256, -1, 2048);
            legendary_durability = builder.defineInRange("legendary", 512, -1, 2048);
            mythic_durability = builder.defineInRange("godly", -1, -1, 2048);

            builder.pop();
        }

    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}