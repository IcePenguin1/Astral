/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 *
 * Copyright © 2023–2024 Jaxydog
 *
 * This file is part of Astral.
 *
 * Astral is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Astral is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with Astral. If not, see <https://www.gnu.org/licenses/>.
 */

package dev.jaxydog.astral;

import dev.jaxydog.astral.content.CustomContent;
import net.fabricmc.api.DedicatedServerModInitializer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The mod's server entrypoint.
 * <p>
 * This class is initialized on the server.
 * <p>
 * It should be assumed that prior to the invocation of {@link #onInitializeServer()}, nothing has been properly loaded.
 * Once the mod has finished loading, the provided {@link #hasInitialized()} method will always return {@code true}.
 * <p>
 * Please modify with a healthy dose of caution!
 *
 * @author Jaxydog
 * @see Astral
 * @see AstralClient
 * @see AstralDataGenerator
 * @since 1.0.0
 */
public final class AstralServer implements DedicatedServerModInitializer {

    /**
     * Tracks whether the mod has been initialized.
     * <p>
     * This will be true only if an instance of the common initializer has fully loaded.
     *
     * @since 2.0.0
     */
    private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);

    /**
     * Returns whether the mod has finished initializing.
     * <p>
     * This method will always return {@code false} during the registration process.
     * <p>
     * If an instance of {@link AstralServer} has been initialized, this will return {@code true}.
     *
     * @return If the mod is initialized.
     *
     * @since 2.0.0
     */
    public static boolean hasInitialized() {
        return INITIALIZED.get();
    }

    @Override
    public void onInitializeServer() {
        if (hasInitialized()) throw new IllegalStateException("The mod may not be initialized more than once");

        CustomContent.INSTANCE.registerServer();

        INITIALIZED.set(true);
    }

}
