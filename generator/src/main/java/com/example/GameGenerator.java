package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.DaoUtil;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class GameGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.jash.dmgame.entities");
        schema.setDefaultJavaPackageDao("com.jash.dmgame.dao");
        Entity type = schema.addEntity("TypeEntity");
        type.addIdProperty().columnName("id");
        type.addStringProperty("typeName");
        Entity chapter = schema.addEntity("ChapterEntity");
        chapter.addIdProperty().columnName("id");
        chapter.addStringProperty("title");
        chapter.addStringProperty("pic");
        chapter.addIntProperty("feedback");
        chapter.addStringProperty("body");
        Property sendDate = chapter.addDateProperty("sendDate").getProperty();
        Property typeId = chapter.addLongProperty("typeId").notNull().getProperty();
        type.addToMany(chapter,typeId).orderDesc(sendDate);
        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
