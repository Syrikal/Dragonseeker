package syric.dragonseeker;

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
//        public final ForgeConfigSpec.ConfigValue<Integer> basic_durability;


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
//        public final ForgeConfigSpec.ConfigValue<Integer> epic_durability;


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
//        public final ForgeConfigSpec.ConfigValue<Integer> legendary_durability;


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
//        public final ForgeConfigSpec.ConfigValue<Integer> mythic_durability;



        public Common(ForgeConfigSpec.Builder builder){

            builder.comment("Basic Dragonseeker").push("basic_dragonseeker");

            basic_optimalDistance = builder
                    .comment("Inside this distance, getting closer to the dragon will not increase the ping chance. Default: 100")
                    .defineInRange("Basic: optimalDistance", 100, 1, 500);
            basic_maxDistance = builder
                    .comment("Maximum distance at which a dragon will be detected. Should be larger than the optimalDistance, but not larger than your render distance. Default: 200")
                    .defineInRange("Basic: maxDistance", 200, 1, 500);
            basic_minPingChance = builder
                    .comment("Minimum chance of a ping. This is the chance of a positive ping when no dragons are detected, i.e. the false positive rate. Default: .12")
                    .defineInRange("Basic: minPingChance", .12, 0, 1);
            basic_maxPingChance = builder
                    .comment("Maximum chance of a ping. This is the chance of a positive ping when there is a dragon within the optimalDistance. Default: .8")
                    .defineInRange("Basic: maxPingChance", .8,
                            0, 1);
            basic_pingCapRadius = builder
                    .comment("Inside this radius, ping properties will not change. The smaller it is, the easier it will be to narrow down a dragon's location. Default: 200")
                    .defineInRange("Basic: pingCapRadius", 200, 1, 500);
            basic_sigPower = builder
                    .comment("Mathematical property governing the relationship between distance and ping properties. The higher this is, the easier it will be to narrow down a dragon's location. Default: 1.5")
                    .defineInRange("Basic: sigPower", 1.5, 1, 5);
            basic_minVol = builder
                    .comment("Minimum volume of a ping, for when there are no dragons in range. Default: .05")
                    .defineInRange("Basic: minVol", .05, 0, 1);
            basic_maxVol = builder
                    .comment("Maximum volume of a ping, for when you're inside the ping cap radius. Default: .05")
                    .defineInRange("Basic: maxVol", .05, 0, 1);
            basic_minPitch = builder
                    .comment("Pitch of a negative result, i.e. no dragon detected. Default: .5")
                    .defineInRange("Basic: minPitch", .5, 0, 1);
            basic_maxPitch = builder
                    .comment("Pitch of a positive result, i.e. there is a dragon detected. Default: .8")
                    .defineInRange("Basic: maxPitch", .8, 0, 1);
            basic_detectsCorpses = builder
                    .comment("Whether or not the dragonseeker detects dead dragons. Default: true")
                    .define("Basic: detectsCorpses", true);
            basic_detectsTame = builder
                    .comment("Whether or not the dragonseeker detects tame dragons. Default: true")
                    .define("Basic: detectsTame", true);
//            basic_durability = builder
//                    .comment("Durability of the item. Set to -1 to make it unbreakable. Default: 128")
//                    .defineInRange("Basic: durability", 128, -1, 4096);
            builder.pop();


            builder.comment("Epic Dragonseeker").push("epic_dragonseeker");

            epic_optimalDistance = builder
                    .comment("Inside this distance, getting closer to the dragon will not increase the ping chance. Default: 150")
                    .defineInRange("Epic: optimalDistance", 150, 1, 500);
            epic_maxDistance = builder
                    .comment("Maximum distance at which a dragon will be detected. Should be larger than the optimalDistance, but not larger than your render distance. Default: 225")
                    .defineInRange("Epic: maxDistance", 225, 1, 500);
            epic_minPingChance = builder
                    .comment("Minimum chance of a ping. This is the chance of a positive ping when no dragons are detected, i.e. the false positive rate. Default: .08")
                    .defineInRange("Epic: minPingChance", .08, 0, 1);
            epic_maxPingChance = builder
                    .comment("Maximum chance of a ping. This is the chance of a positive ping when there is a dragon within the optimalDistance. Default: .9")
                    .defineInRange("Epic: maxPingChance", .9, 0, 1);
            epic_pingCapRadius = builder
                    .comment("Inside this radius, ping properties will not change. The smaller it is, the easier it will be to narrow down a dragon's location. Default: 125")
                    .defineInRange("Epic: pingCapRadius", 125, 1, 500);
            epic_sigPower = builder
                    .comment("Mathematical property governing the relationship between distance and ping properties. The higher this is, the easier it will be to narrow down a dragon's location. Default: 1.5")
                    .defineInRange("Epic: sigPower", 1.5, 1, 5);
            epic_minVol = builder
                    .comment("Minimum volume of a ping, for when there are no dragons in range. Default: .05")
                    .defineInRange("Epic: minVol", .05, 0, 1);
            epic_maxVol = builder
                    .comment("Maximum volume of a ping, for when you're inside the ping cap radius. Default: .4")
                    .defineInRange("Epic: maxVol", .4, 0, 1);
            epic_minPitch = builder
                    .comment("Pitch of a negative result, i.e. no dragon detected. Default: .5")
                    .defineInRange("Epic: minPitch", .5, 0, 1);
            epic_maxPitch = builder
                    .comment("Pitch of a positive result, i.e. there is a dragon detected. Default: .8")
                    .defineInRange("Epic: maxPitch", .8, 0, 1);
            epic_detectsCorpses = builder
                    .comment("Whether or not the dragonseeker detects dead dragons. Default: true")
                    .define("Epic: detectsCorpses", true);
            epic_detectsTame = builder
                    .comment("Whether or not the dragonseeker detects tame dragons. Default: false")
                    .define("Epic: detectsTame", false);
//            epic_durability = builder
//                    .comment("Durability of the item. Set to -1 to make it unbreakable. Default: 256")
//                    .defineInRange("Epic: durability", 256, -1, 4096);
            builder.pop();


            builder.comment("Legendary Dragonseeker").push("legendary_dragonseeker");

            legendary_optimalDistance = builder
                    .comment("Inside this distance, getting closer to the dragon will not increase the ping chance. Default: 200")
                    .defineInRange("Legendary: optimalDistance", 200, 1, 500);
            legendary_maxDistance = builder
                    .comment("Maximum distance at which a dragon will be detected. Should be larger than the optimalDistance, but not larger than your render distance. Default: 250")
                    .defineInRange("Legendary: maxDistance", 250, 1, 500);
            legendary_minPingChance = builder
                    .comment("Minimum chance of a ping. This is the chance of a positive ping when no dragons are detected, i.e. the false positive rate. Default: .04")
                    .defineInRange("Legendary: minPingChance", .04, 0, 1);
            legendary_maxPingChance = builder
                    .comment("Maximum chance of a ping. This is the chance of a positive ping when there is a dragon within the optimalDistance. Default: .95")
                    .defineInRange("Legendary: maxPingChance", .95, 0, 1);
            legendary_pingCapRadius = builder
                    .comment("Inside this radius, ping properties will not change. The smaller it is, the easier it will be to narrow down a dragon's location. Default: 100")
                    .defineInRange("Legendary: pingCapRadius", 100, 1, 500);
            legendary_sigPower = builder
                    .comment("Mathematical property governing the relationship between distance and ping properties. The higher this is, the easier it will be to narrow down a dragon's location. Default: 2.5")
                    .defineInRange("Legendary: sigPower", 2.5, 1, 5);
            legendary_minVol = builder
                    .comment("Minimum volume of a ping, for when there are no dragons in range. Default: .05")
                    .defineInRange("Legendary: minVol", .05, 0, 1);
            legendary_maxVol = builder
                    .comment("Maximum volume of a ping, for when you're inside the ping cap radius. Default: .7")
                    .defineInRange("Legendary: maxVol", .7, 0, 1);
            legendary_minPitch = builder
                    .comment("Pitch of a negative result, i.e. no dragon detected. Default: .5")
                    .defineInRange("Legendary: minPitch", .5, 0, 1);
            legendary_maxPitch = builder
                    .comment("Pitch of a positive result, i.e. there is a dragon detected. Default: .8")
                    .defineInRange("Legendary: maxPitch", .8, 0, 1);
            legendary_detectsCorpses = builder
                    .comment("Whether or not the dragonseeker detects dead dragons. Default: false")
                    .define("Legendary: detectsCorpses", false);
            legendary_detectsTame = builder
                    .comment("Whether or not the dragonseeker detects tame dragons. Default: false")
                    .define("Legendary: detectsTame", false);
//            legendary_durability = builder
//                    .comment("Durability of the item. Set to -1 to make it unbreakable. Default: 512")
//                    .defineInRange("Legendary: durability", 512, -1, 4096);
            builder.pop();


            builder.comment("Godly Dragonseeker").push("mythic_dragonseeker");

            mythic_optimalDistance = builder
                    .comment("Inside this distance, getting closer to the dragon will not increase the ping chance. Default: 500")
                    .defineInRange("Godly: optimalDistance", 500, 1, 500);
            mythic_maxDistance = builder
                    .comment("Maximum distance at which a dragon will be detected. Should be larger than the optimalDistance, but not larger than your render distance. Default: 500")
                    .defineInRange("Godly: maxDistance", 500, 1, 500);
            mythic_minPingChance = builder
                    .comment("Minimum chance of a ping. This is the chance of a positive ping when no dragons are detected, i.e. the false positive rate. Default: 0")
                    .defineInRange("Godly: minPingChance", 0D, 0, 1);
            mythic_maxPingChance = builder
                    .comment("Maximum chance of a ping. This is the chance of a positive ping when there is a dragon within the optimalDistance. Default: 1")
                    .defineInRange("Godly: maxPingChance", 1D, 0, 1);
            mythic_pingCapRadius = builder
                    .comment("Inside this radius, ping properties will not change. The smaller it is, the easier it will be to narrow down a dragon's location. Default: 0")
                    .defineInRange("Godly: pingCapRadius", 0, 1, 500);
            mythic_sigPower = builder
                    .comment("Mathematical property governing the relationship between distance and ping properties. The higher this is, the easier it will be to narrow down a dragon's location. Default: 3.5")
                    .defineInRange("Godly: sigPower", 3.5, 1, 5);
            mythic_minVol = builder
                    .comment("Minimum volume of a ping, for when there are no dragons in range. Default: .05")
                    .defineInRange("Godly: minVol", .05, 0, 1);
            mythic_maxVol = builder
                    .comment("Maximum volume of a ping, for when you're inside the ping cap radius. Default: 1")
                    .defineInRange("Godly: maxVol", 1D, 0, 1);
            mythic_minPitch = builder
                    .comment("Pitch of a negative result, i.e. no dragon detected. Default: .5")
                    .defineInRange("Godly: minPitch", .5, 0, 1);
            mythic_maxPitch = builder
                    .comment("Pitch of a positive result, i.e. there is a dragon detected. Default: 1")
                    .defineInRange("Godly: maxPitch", 1D, 0, 1);
            mythic_detectsCorpses = builder
                    .comment("Whether or not the dragonseeker detects dead dragons. Default: false")
                    .define("Godly: detectsCorpses", false);
            mythic_detectsTame = builder
                    .comment("Whether or not the dragonseeker detects tame dragons. Default: false")
                    .define("Godly: detectsTame", false);
//            mythic_durability = builder
//                    .comment("Durability of the item. Set to -1 to make it unbreakable. Default: -1")
//                    .defineInRange("Godly: durability", -1, -1, 4096);
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