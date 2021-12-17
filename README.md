# Achiever
Achievement plugin for Spigot. Meant to be a simple to use yet decent plugin for those in need of simple Achievements. Support for Vault, Entering/Leaving a Region using WorldGuard and the flexibility to support custom types. Currently a work in progress 

### Configuration
The configuration for this plugin is quite easy. Go into the data folder(usually `/plugins/Achiever`) and edit the `achievements.yml` file. An example configuration:
```yml
achievements:
  vault_withdraw1:
    type: vault_withdraw
    expected: 4.20
  vault_deposit1:
    type: vault_deposit
    expected: 6.90
```
> **NOTE:** every achievement consists of a type and expected value

The raw format for an achievement is:
```yml
achievements:
  <id>:
    type: <type>
    expected: <expected value>
```

### Custom Addon
The available achievement types can be extended by adding a `IAchievementType<T>` to the `AchievementTypeService` using the following code:
```java
IAchievementType<T> type;
Achiever.service(AchievementTypeService.class).add(type);
```
The simplest way to add your own type is to extend `AbstractAchievementType` since this already implements most of the methods needed. You then only have to write your own `load()` method that gets fired once whenever the type is used. The load method needs to register whatever data listener you require that on its own fires the `check(<uuid>, <id>, <value>)` method with the latest value for the achievement type:
```java
UUID uuid;//Uuid of player
String id;//Achievement type id
Object value;//Object with value of same type as achievement type
        
//Check achievements of type for value
Achiever.service(IAchievementDataService.class).check(uuid, id, value);
```

#### Example
Example Type:
```java
public class MyCustomType extends AbstractAchievementType<Integer> {
    
    public MyCustomType() {
        super("my_id", Integer.class);
    }
    
    public void load() {
        //Register data listener
    }
    
}
```

What to do in data listener:
```java
UUID uuid;//Uuid of player
Integer value;//Current value of type to check
Achiever.service(IAchievementDataService.class).check(uuid, "my_id", value);
```