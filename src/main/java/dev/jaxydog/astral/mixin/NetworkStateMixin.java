/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 *
 * Copyright © 2024 Jaxydog
 *
 * This file is part of Astral.
 *
 * Astral is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Astral is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with Astral. If not, see <https://www.gnu.org/licenses/>.
 */

package dev.jaxydog.astral.mixin;

import dev.jaxydog.astral.utility.PlayUnboundedSoundFromEntityS2CPacket;
import dev.jaxydog.astral.utility.PlayUnboundedSoundS2CPacket;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.NetworkState;
import net.minecraft.network.NetworkState.PacketHandler;
import net.minecraft.network.NetworkState.PacketHandlerInitializer;
import net.minecraft.network.PacketBundleHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.Function;

/**
 * Registers custom packet types to allow for their usage in networking.
 *
 * @author Jaxydog
 * @since 1.7.0
 */
@Mixin(NetworkState.class)
public abstract class NetworkStateMixin implements PacketBundleHandler.BundlerGetter {

    /**
     * Registers a custom packet.
     *
     * @param handler The packet handler instance.
     * @param type The packet type.
     * @param factory The packet creation factory.
     * @param <T> The type of the packet's listener.
     * @param <P> The type of the packet.
     *
     * @since 1.7.0
     */
    @SuppressWarnings("unchecked")
    @Unique
    private <T extends PacketListener, P extends Packet<T>> void register(
        PacketHandler<?> handler, Class<P> type, Function<PacketByteBuf, P> factory
    ) {
        ((PacketHandler<T>) handler).register(type, factory);
    }

    /**
     * Registers custom packets during initialization.
     *
     * @param name The name of this variant.
     * @param ordinal The ordinal integer for this variant.
     * @param id The numeric identifier for this variant.
     * @param initializer The packet handler initializer instance.
     * @param ci The injection callback information.
     *
     * @since 1.7.0
     */
    @Inject(
        method = "<init>", at = @At(
        value = "FIELD",
        target = "Lnet/minecraft/network/NetworkState$PacketHandlerInitializer;packetHandlers:Ljava/util/Map;"
    )
    )
    private void addPlayPackets(
        String name, int ordinal, int id, PacketHandlerInitializer initializer, CallbackInfo ci
    ) {
        // Ensure we initialize within the correct enum variant.
        if (id != 0) return;

        final PacketHandlerInitializerAccess access = (PacketHandlerInitializerAccess) initializer;
        final PacketHandler<?> handler = access.getPacketHandlers().get(NetworkSide.CLIENTBOUND);

        this.register(handler, PlayUnboundedSoundS2CPacket.class, PlayUnboundedSoundS2CPacket::new);
        this.register(handler, PlayUnboundedSoundFromEntityS2CPacket.class, PlayUnboundedSoundFromEntityS2CPacket::new);
    }

    /**
     * Allows access to a map of packet handlers.
     *
     * @author Jaxydog
     * @since 1.7.0
     */
    @Mixin(PacketHandlerInitializer.class)
    public interface PacketHandlerInitializerAccess {

        /**
         * Returns a map of network sides and packet handlers.
         *
         * @return A map of network sides and packet handlers.
         *
         * @since 1.7.0
         */
        @Accessor("packetHandlers")
        Map<NetworkSide, PacketHandler<?>> getPacketHandlers();

    }

}
