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
   
	[Tooltip("The size of a grid square in the game\n" +
              "that corospond to the pixels")]
    //end of start section