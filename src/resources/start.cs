using UnityEngine;

public class LevelGenerator : MonoBehaviour {

     [Tooltip("The texture that will be used to draw\n" +
              "the level.")]
    public Texture2D level;

     [Tooltip("The size of a grid square in the game\n" +
              "that corospond to the pixels")]
     public float gridSize = 32f;
     //end of start section