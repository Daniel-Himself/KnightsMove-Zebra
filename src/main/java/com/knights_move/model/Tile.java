package com.knights_move.model;

public class Tile {
    private Position tilePosition;
    private TypeTile type;
    private Color tileColor;
    private Question tileQuestion;

    private boolean isVisited;

    public Tile(Position tilePosition, TypeTile type, Color tileColor, Question tileQuestion, boolean isVisited) {
        this.tilePosition = tilePosition;

        /*if(type.equals("EMPTY"))
            this.setType(type.EMPTY);
        if(type.equals("VISITED"))
            this.setType(type.VISITED);
        if(type.equals("RANDOMPJUMP"))
            this.setType(type.RANDOMPJUMP);
        if(type.equals("FORGOTTEN"))
            this.setType(type.FORGOTTEN);
        if(type.equals("BLOCKED"))
            this.setType(type.BLOCKED);
        if(type.equals("SPECIAL"))
            this.setType(type.SPECIAL);*/



        this.type = type;
        this.tileColor = tileColor;
        /*if(tileColor.equals("BLUE"))
            this.setTileColor(tileColor.BLUE);
        if(tileColor.equals("RED"))
            this.setTileColor(tileColor.RED);
        if(tileColor.equals("WHITE"))
            this.setTileColor(tileColor.WHITE);*/

        this.tileQuestion = tileQuestion;
        this.isVisited = isVisited;
    }


    public Tile(Position tilePosition, TypeTile type, Color tileColor, boolean isVisited) {
        this.tilePosition = tilePosition;

        /*if(type.equals("EMPTY"))
            this.setType(type.EMPTY);
        if(type.equals("VISITED"))
            this.setType(type.VISITED);
        if(type.equals("RANDOMPJUMP"))
            this.setType(type.RANDOMPJUMP);
        if(type.equals("FORGOTTEN"))
            this.setType(type.FORGOTTEN);
        if(type.equals("BLOCKED"))
            this.setType(type.BLOCKED);
        if(type.equals("SPECIAL"))
            this.setType(type.SPECIAL);*/



        this.type = type;
        this.tileColor = tileColor;
        /*if(tileColor.equals("BLUE"))
            this.setTileColor(tileColor.BLUE);
        if(tileColor.equals("RED"))
            this.setTileColor(tileColor.RED);
        if(tileColor.equals("WHITE"))
            this.setTileColor(tileColor.WHITE);*/

        this.isVisited = isVisited;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public Position getTilePosition() {
        return tilePosition;
    }

    public void setTilePosition(Position tilePosition) {
        this.tilePosition = tilePosition;
    }

    public TypeTile getType() {
        return type;
    }

    public void setType(TypeTile type) {
        this.type = type;
    }

    public Color getTileColor() {
        return tileColor;
    }

    public void setTileColor(Color tileColor) {
        this.tileColor = tileColor;
    }

    public Question getTileQuestion() {
        return tileQuestion;
    }

    public void setTileQuestion(Question tileQuestion) {
        this.tileQuestion = tileQuestion;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "tilePosition=" + tilePosition +
                ", type=" + type +
                ", tileColor=" + tileColor +
                ", tileQuestion=" + tileQuestion +
                '}';
    }
    //CHANGEPOSITION - NOT HERE
    //public Question popQuestion(){
    //}

    //public boolean removeQuestion(int Question){}

    /**
     * visitedTile - blue
     * blockedTile - red
     * empty tile or any other special tiles - white
     */


    public void changeColor(){
        try {
            //EMPTY, VISITED, RANDOMPJUMP, FORGOTTEN, BLOCKED, SPECIAL
            if (tilePosition != null) {
                if(type.equals(TypeTile.VISITED))
                    setTileColor(Color.BLUE);
                else if(type.equals(TypeTile.BLOCKED))
                    setTileColor(Color.RED);
                    //if type of tile is empty or  RANDOMPJUMP,FORGOTTEN, SPECIAL, then set color of tile to white.
                else
                    setTileColor(Color.WHITE);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
