using UnityEngine;

public class GameObjectLevelGenerator : MonoBehaviour {

    [Tooltip("The texture that will be used to draw\n" +
             "the level.")]
    public Texture2D[] levelLayers;
    [Space(10)]
    [Tooltip("The size of a grid square in the game\n" +
             "that corospond to the pixels")]
    //end of start section