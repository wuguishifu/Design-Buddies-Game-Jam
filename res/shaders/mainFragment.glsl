#version 460 core

// the input values
in vec2 passTextureCoord;

// the output values
out vec4 outColor;

// the uniform
uniform sampler2D tex;

// main method
void main() {
    outColor = texture(tex, passTextureCoord);
}