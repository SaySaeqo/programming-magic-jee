package pl.edu.pg.eti.kask.s180171.programmingmagic

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Global config for JAX-RS REST services prefix.
 */
@ApplicationPath("/api")
public class ApplicationConfig extends Application {}