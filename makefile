# Detect OS (Windows or Unix-based)
ifeq ($(OS),Windows_NT)
    MKDIR = if not exist $(BUILD_DIR) mkdir $(BUILD_DIR)
    RM = rmdir /s /q $(BUILD_DIR)
    SHELL := cmd.exe
else
    MKDIR = mkdir -p $(BUILD_DIR)
    RM = rm -rf $(BUILD_DIR)
endif

# Project details
SRC_DIR = src
BUILD_DIR = build
MAIN_CLASS = ArkanoidGame

# Run the Java project
run: compile
	java -cp $(BUILD_DIR) $(MAIN_CLASS)

# Compile the Java project
compile:
	$(MKDIR)
	javac -d $(BUILD_DIR) $(SRC_DIR)/geometry_primitives/*.java $(SRC_DIR)/sprite_settings/*.java $(SRC_DIR)/*.java
	@echo "Compilation completed."

# Clean the build directory
clean:
	$(RM)
	@echo "Build cleaned."
