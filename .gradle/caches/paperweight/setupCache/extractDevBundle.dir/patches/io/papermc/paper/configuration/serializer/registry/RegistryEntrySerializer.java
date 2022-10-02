package io.papermc.paper.configuration.serializer.registry;

import io.leangen.geantyref.TypeToken;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.serialize.ScalarSerializer;
import org.spongepowered.configurate.serialize.SerializationException;

import java.lang.reflect.Type;
import java.util.function.Predicate;

abstract class RegistryEntrySerializer<T, R> extends ScalarSerializer<T> {

    private final ResourceKey<? extends Registry<R>> registryKey;
    private final boolean omitMinecraftNamespace;

    protected RegistryEntrySerializer(TypeToken<T> type, ResourceKey<? extends Registry<R>> registryKey, boolean omitMinecraftNamespace) {
        super(type);
        this.registryKey = registryKey;
        this.omitMinecraftNamespace = omitMinecraftNamespace;
    }

    protected RegistryEntrySerializer(Class<T> type, ResourceKey<? extends Registry<R>> registryKey, boolean omitMinecraftNamespace) {
        super(type);
        this.registryKey = registryKey;
        this.omitMinecraftNamespace = omitMinecraftNamespace;
    }

    protected final Registry<R> registry() {
        return MinecraftServer.getServer().registryAccess().registryOrThrow(this.registryKey);
    }

    protected abstract T convertFromResourceKey(ResourceKey<R> key) throws SerializationException;

    @Override
    public final T deserialize(Type type, Object obj) throws SerializationException {
        return this.convertFromResourceKey(this.deserializeKey(obj));
    }

    protected abstract ResourceKey<R> convertToResourceKey(T value);

    @Override
    protected final Object serialize(T item, Predicate<Class<?>> typeSupported) {
        final ResourceKey<R> key = this.convertToResourceKey(item);
        if (this.omitMinecraftNamespace && key.location().getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
            return key.location().getPath();
        } else {
            return key.location().toString();
        }
    }

    private ResourceKey<R> deserializeKey(final Object input) throws SerializationException {
        final @Nullable ResourceLocation key = ResourceLocation.tryParse(input.toString());
        if (key == null) {
            throw new SerializationException("Could not create a key from " + input);
        }
        return ResourceKey.create(this.registryKey, key);
    }
}
