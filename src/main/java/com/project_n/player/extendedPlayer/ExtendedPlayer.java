package com.project_n.player.extendedPlayer;

import com.project_n.Project_N_Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by liam on 18/02/2017.
 */
public class ExtendedPlayer implements IExtendedEntityProperties
{
    public final static String IDENTIFIER = "project_n_playerdata";

    private final EntityPlayer player;

    //Basic player traits such as stamina and chakra amount, speed, strength and mind
    private int playerChakra, maxPlayerChakra,playerStamina, maxPlayerStamina, playerChakraRegenerationCooldown, playerStaminaRegenerationCooldown, playerSpeed, playerStrength, playerMind;

    //Players affinities toward nature types
    private int natureAffinityFire, natureAffinityWind, natureAffinityLightning, natureAffinityEarth, natureAffinityWater;

    //players affinities toward ying, yang and yinyang
    private int affinityYang, affinityYin, affinityYinYang;

    //Used as a way to show off to friends. Basically level system to say haha I'm level 16
    private int ninjaExperienceLevel, getNinjaExperienceLevelXP;

    //The players health
    private int playerHealth, playerMaxHealth;

    //Players skills
    private int playerTaijutsu, playerGenjutsu, playerFuinjutsu, playerNinjutsu, playerDojutsu;

    private String rolePlayName;

    private String ninjaRank;

    private int PLAYER_CLAN_WATCHER = 21;

    //The players clan
    public String playerClan = "Undefined";

    //ExtendedPlayer constructor method
    public ExtendedPlayer(EntityPlayer player)
    {
        this.player = player;
    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(ExtendedPlayer.IDENTIFIER, new ExtendedPlayer(player));
    }

    public static final ExtendedPlayer get(EntityPlayer player)
    {
        return (ExtendedPlayer) player.getExtendedProperties(IDENTIFIER);
    }

    public void copyPlayerData(ExtendedPlayer playerProperties)
    {
        this.playerClan = playerProperties.playerClan;
        player.getDataWatcher().updateObject(PLAYER_CLAN_WATCHER, playerClan);
    }

    public void reloadDW()
    {
        player.getDataWatcher().updateObject(PLAYER_CLAN_WATCHER, "Undefined");

        Project_N_Main.logger.info("Player Clan: " + playerClan);
        player.getDataWatcher().updateObject(PLAYER_CLAN_WATCHER, playerClan);
    }


    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();

        properties.setString("PlayerClan", playerClan);
        properties.setInteger("CurrentPlayerChakra", this.playerChakra);
        properties.setInteger("CurrentPlayerStamina", this.playerStamina);
        properties.setInteger("MaxPlayerChakra", this.maxPlayerChakra);
        properties.setInteger("MaxPlayerStamina", this.maxPlayerStamina);

        compound.setTag(IDENTIFIER, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(IDENTIFIER);

        this.playerClan = properties.getString("PlayerClan");
        this.playerChakra = properties.getInteger("CurrentPlayerChakra");
        this.playerStamina = properties.getInteger("CurrentPlayerStamina");
        this.maxPlayerChakra = properties.getInteger("MaxChakra"); // This will need to be caculated based on level and point amount
        this.maxPlayerStamina = properties.getInteger("MaxStamina"); // This will need to be caculated based on level and point amount
        this.reloadDW();

        Project_N_Main.logger.info("Current chakra for player from NBT: " + this.playerChakra + "/" + this.maxPlayerChakra);
        Project_N_Main.logger.info("Current Stamina for player from NBT: " + this.playerStamina + "/" + this.maxPlayerStamina);

    }

    @Override
    public void init(Entity entity, World world)
    {

    }

    public boolean consumePlayerChakra(int amount)
    {
        boolean sufficient = amount <= this.playerChakra;

        this.playerChakra -= (sufficient ? amount : 0);

        return sufficient;
    }

    public void regenChakra(int amount)
    {
        boolean sufficient = amount <= this.playerChakra;

        this.playerChakra += (amount < this.playerChakra ? amount : this.maxPlayerChakra);
    }

    public void replenishPlayerChakra()
    {
        this.playerChakra = this.maxPlayerChakra;
    }

    public void setPlayerClan(String playerClan)
    {
        this.playerClan = playerClan;
    }
}
