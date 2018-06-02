#if UNITY_EDITOR
using UnityEngine;
using UnityEditor;

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
public class EntityArrayAttribute : PropertyAttribute
{
	public readonly string[] names;
	public EntityArrayAttribute(string[] names) {
		this.names = names;
	}
}
#endif