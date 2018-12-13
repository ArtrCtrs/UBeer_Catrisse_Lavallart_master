package org.esiea.catrisse_lavallart.ubeer.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.esiea.catrisse_lavallart.ubeer.model.Bar;

public class BarBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "bar.db";

    private static final String TABLE_BAR = "table_bar";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "name";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_ADDRESS = "address";
    private static final int NUM_COL_ADDRESS = 2;
    private static final String COL_ISOPEN = "isopen";
    private static final int NUM_COL_ISOPEN = 3;
    private static final String COL_RANK = "rank";
    private static final int NUM_COL_RANK = 4;
    private static final String COL_URL = "url";
    private static final int NUM_COL_URL = 5;

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public BarBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertBar(Bar bar){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_NAME, bar.getName());
        values.put(COL_ADDRESS, bar.getAddress());
        values.put(COL_ISOPEN, bar.getIsOpen());
        values.put(COL_RANK, bar.getRank());
        values.put(COL_URL, bar.getUrl());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_BAR, null, values);
    }


    public int removeBarWithName(String name){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_BAR, COL_NAME + " = " +name, null);
    }
    public int removeBar(){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_BAR,null,null) ;
    }

    public Bar getBarwithName(String name){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_BAR, new String[] {COL_ID, COL_NAME, COL_ADDRESS,COL_ISOPEN,COL_RANK,COL_URL}, COL_NAME + " LIKE \"" + name +"\"", null, null, null, null);
        return cursorToBar(c);
    }
    public Bar getBarwitindex(){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_BAR, new String[] {COL_ID, COL_NAME, COL_ADDRESS,COL_ISOPEN,COL_RANK,COL_URL}, null, null, null, null, null);
        return cursorToBar(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Bar cursorToBar(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Bar bar = new Bar();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        bar.setName(c.getString(NUM_COL_NAME));
        bar.setAddress(c.getString(NUM_COL_ADDRESS));
        bar.setIsopen(c.getString(NUM_COL_ISOPEN));
        bar.setRank(c.getString(NUM_COL_RANK));
        bar.setUrl(c.getString(NUM_COL_URL));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return bar;
    }
}