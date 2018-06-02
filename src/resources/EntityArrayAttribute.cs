#if UNITY_EDITOR
using UnityEngine;
using UnityEditor;

public class EntityArrayAttribute : PropertyAttribute
{
	public readonly string[] names;
	public EntityArrayAttribute(string[] names) {
		this.names = names;
	}
}
#endif