using System.Collections;
using System.Collections.Generic;
using UnityEngine;
#if UNITY_EDITOR
using UnityEditor;
#endif

/// <summary>
/// Strictly a helper class for the convenience of the developer. If you place
/// all your GameObjects (by dragging and dropping them in large amounts)
/// into the array(s) of this script, then press the "Fill Entities" button,
/// they will be loaded into the GameObjectLevelGenerator script's entities.
/// this way, you don't have to drag and drop them individually. You can
/// delete this script after use.
/// 
/// It is a separate script on purpose, so that you can easily reset the
/// associated GameObjectLevelGenerator script without loosing your lists of 
/// GameObjects in the "GameObjectEntityFiller.gameObjects" array.
/// </summary>
[RequireComponent(typeof(GameObjectLevelGenerator))]
public class GameObjectEntityFiller : MonoBehaviour {

    /// <summary>
    /// The GameObjects that the user wants to load (alphabetically) into
    /// the Entity objects of the LevelGenerator script
    /// </summary>
    public GameObject[] gameObjects;

    public void FillEntities() {
        //get a reference to the LevelGenerator component
        GameObjectLevelGenerator levelGenerator = GetComponent<GameObjectLevelGenerator>();
        //if we have a link to the LevelGenerator component
        if (levelGenerator != null) {
            //get the Entities array
            GameObjectEntity[] entities = levelGenerator.entitiesArray;
            //loop through all the GameObjects the user put in this script
            for (int i = 0; i < gameObjects.Length; i++) {
                //if i is less than the length of the Entities array
                if (i < entities.Length) {
                    //set the corosponding Entity's prefab to this GameObject
                    entities[i].prefab = gameObjects[i];
                }
            }
        } else {
            Debug.LogError("This GameObject has no LevelGenerator component.");
        }
    }

}

#if UNITY_EDITOR
/// <summary>
/// Creates a button at the bottom of the script that allows users to
/// quickly and easily fill in the GameObjects of their array of
/// GameObjectEntity objects. A user may drag and drop all of their
/// GameObject into the "GameObjectEntityFiller.tiles" array (in
/// alphabetical order). Then the user may press the "Fill
/// Entities" button and the GameObject will be sent
/// instantly to the "GameObjectLevelGenerator.gameObjectEntnties" array.
/// </summary>
[CustomEditor(typeof(GameObjectEntityFiller))]
class GameObjectEntityFillerEditor : Editor {

    public override void OnInspectorGUI() {
        base.OnInspectorGUI();
        //get the script with the tiles
        GameObjectEntityFiller myScript = (GameObjectEntityFiller)target;
        //if the user pressed the "Fill Entities" button
        if (GUILayout.Button("Fill Entities")) {
            //fill the Entities
            myScript.FillEntities();
        }
    }
}
#endif
