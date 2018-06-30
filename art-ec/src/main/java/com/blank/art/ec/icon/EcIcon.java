package com.blank.art.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 16:05
 * Description:
 */

public enum EcIcon implements Icon {

    icon_car('\ue651'),

    icon_smallcar('\ue622');


    private char character;

    EcIcon(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
