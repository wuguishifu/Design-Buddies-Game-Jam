#version 460 core

// the layouts
layout(location = 0) in vec3 position;
layout(location = 1) in vec2 textureCoord;

// the output
out vec3 passColor;
out vec2 passTextureCoord;

// the uniforms
uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

// main method
void main() {
    gl_Position = projection * view * model * vec4(position, 1.0); // must be MVP or PVM
    passTextureCoord = textureCoord;
}
