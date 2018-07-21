using System;
using UnityEngine;
using UnityEngine.Tilemaps;

public class TileLevelGenerator : MonoBehaviour {

    private GameObject grid;
    private Tilemap[] tilemapComponents;

    [Tooltip("The textures that will be used to draw\n" +
             "the tilemap. Each texture is a layer of\n" +
             "level.")]
    public Texture2D[] levelLayers;
    [Space(10)]
    //end of start section
    