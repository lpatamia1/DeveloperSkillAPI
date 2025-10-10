# ================================
# 💚 Developer Skill API Makefile
# Java Spring Boot | Maven Project
# ================================

# Variables
APP_NAME=DeveloperSkillAPI
JAR_FILE=target/$(APP_NAME).jar
PORT=8080

# Colors for output
GREEN=\033[0;32m
CYAN=\033[0;36m
YELLOW=\033[1;33m
RESET=\033[0m

# ----------------
# Primary Commands
# ----------------

.PHONY: help build run clean test format install swagger

help:
	@echo "$(CYAN)Available Commands for $(APP_NAME):$(RESET)"
	@echo "  $(GREEN)make build$(RESET)   - Compile the project and package into a JAR"
	@echo "  $(GREEN)make run$(RESET)     - Run the Spring Boot application"
	@echo "  $(GREEN)make clean$(RESET)   - Remove build artifacts"
	@echo "  $(GREEN)make test$(RESET)    - Run all unit tests with Maven"
	@echo "  $(GREEN)make install$(RESET) - Install dependencies via Maven"
	@echo "  $(GREEN)make format$(RESET)  - Auto-format Java code using Maven"
	@echo "  $(GREEN)make swagger$(RESET) - Open Swagger UI (if running on localhost)"
	@echo ""
	@echo "Example: $(YELLOW)make run$(RESET)"

# -----------------
# Build & Execution
# -----------------

build:
	@echo "$(CYAN)📦 Building $(APP_NAME)...$(RESET)"
	mvn clean package -DskipTests
	@echo "$(GREEN)✅ Build complete! JAR created at $(JAR_FILE)$(RESET)"

run:
	@echo "$(CYAN)🚀 Starting $(APP_NAME) on port $(PORT)...$(RESET)"
	mvn spring-boot:run

clean:
	@echo "$(YELLOW)🧹 Cleaning project...$(RESET)"
	mvn clean
	@echo "$(GREEN)✅ Cleanup complete.$(RESET)"

# ----------------
# Testing & Tools
# ----------------

test:
	@echo "$(CYAN)🧪 Running tests...$(RESET)"
	mvn test
	@echo "$(GREEN)✅ Tests completed.$(RESET)"

install:
	@echo "$(CYAN)📦 Installing dependencies...$(RESET)"
	mvn install -DskipTests
	@echo "$(GREEN)✅ Dependencies installed.$(RESET)"

format:
	@echo "$(CYAN)✨ Formatting code...$(RESET)"
	mvn spotless:apply || echo "⚠️ Spotless not configured (optional plugin)"
	@echo "$(GREEN)✅ Code formatting complete.$(RESET)"

swagger:
	@echo "$(CYAN)🌐 Opening Swagger UI...$(RESET)"
	@start "" "http://localhost:$(PORT)/swagger-ui.html" || true
