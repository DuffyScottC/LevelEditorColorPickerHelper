using UnityEngine;

public class LevelGenerator : MonoBehaviour {

     [Tooltip("The texture that will be used to draw\n" +
              "the level.")]
    public Texture2D level;

     [Tooltip("The size of a grid square in the game\n" +
              "that corospond to the pixels")]
     public float gridSize = 32f;

#if UNITY_EDITOR
     /*
     * This EntityAttribute is only neccessary for viewing
     * the ColorToPrefab objects within the Inspector, so it
     * is not included in the final build. The EntityAttribute
     * holds the names of your entities to help you keep track
     * of them in the inspector.
     * 
     * The entities array holds Entity objects that are initialized
     * with the color you specified in your project.
     */
     //end of start section