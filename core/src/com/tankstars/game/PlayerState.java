package com.tankstars.game;

import java.io.Serializable;

public class PlayerState implements Serializable {
    public String name;
    public Integer health;
    public Integer positionX;
    public Integer positionY;
    public String tankName;
}
