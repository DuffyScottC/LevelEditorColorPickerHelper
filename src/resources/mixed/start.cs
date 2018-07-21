using System;
using UnityEngine;
using UnityEngine.Tilemaps;
/// <summary>
/// Used for when there are Tiles and Prefabs in the same image.
/// </summary>
public class MixedLevelGenerator : MonoBehaviour {

    private GameObject grid;
    private Tilemap[] tilemapComponents;

    [Tooltip("The textures that will be used to draw\n" +
             "the level's layers. Each texture is a\n" +
             "layer of the level.")]
    public Texture2D[] levelLayers;
    [Space(10)]
    //end of start section
