// Auth Pages Colorful JavaScript

document.addEventListener("DOMContentLoaded", () => {
  // Theme Toggle
  const themeToggle = document.getElementById("theme-toggle")
  const htmlElement = document.documentElement

  // Check for saved theme preference or use system preference
  const savedTheme = localStorage.getItem("theme")
  const systemPrefersDark = window.matchMedia && window.matchMedia("(prefers-color-scheme: dark)").matches

  // Set initial theme
  if (savedTheme === "dark" || (!savedTheme && systemPrefersDark)) {
    htmlElement.classList.add("dark-mode")
  }

  // Toggle theme when button is clicked
  if (themeToggle) {
    themeToggle.addEventListener("click", () => {
      htmlElement.classList.toggle("dark-mode")

      // Save theme preference
      if (htmlElement.classList.contains("dark-mode")) {
        localStorage.setItem("theme", "dark")
      } else {
        localStorage.setItem("theme", "light")
      }
    })
  }

  // Password Toggle
  const togglePasswordButtons = document.querySelectorAll(".toggle-password")

  togglePasswordButtons.forEach((button) => {
    button.addEventListener("click", function () {
      const input = this.previousElementSibling
      const eyeIcon = this.querySelector(".eye-icon")
      const eyeOffIcon = this.querySelector(".eye-off-icon")

      if (input.type === "password") {
        input.type = "text"
        eyeIcon.style.display = "none"
        eyeOffIcon.style.display = "block"
      } else {
        input.type = "password"
        eyeIcon.style.display = "block"
        eyeOffIcon.style.display = "none"
      }
    })
  })

  // Password Strength Meter
  const signupPassword = document.getElementById("signupPassword")
  if (signupPassword) {
    const strengthSegments = document.querySelectorAll(".strength-segment")
    const strengthText = document.querySelector(".strength-text")

    signupPassword.addEventListener("input", function () {
      const password = this.value
      const strength = calculatePasswordStrength(password)

      // Update strength meter
      strengthSegments.forEach((segment, index) => {
        if (index < strength) {
          segment.style.backgroundColor = getStrengthColor(strength)
        } else {
          segment.style.backgroundColor = "var(--border-color)"
        }
      })

      // Update strength text
      strengthText.textContent = getStrengthText(strength)
      strengthText.style.color = getStrengthColor(strength)
    })

    function calculatePasswordStrength(password) {
      if (!password) return 0

      let strength = 0

      // Length check
      if (password.length >= 8) strength += 1

      // Contains lowercase
      if (/[a-z]/.test(password)) strength += 1

      // Contains uppercase
      if (/[A-Z]/.test(password)) strength += 1

      // Contains number
      if (/[0-9]/.test(password)) strength += 1

      // Contains special character
      if (/[^A-Za-z0-9]/.test(password)) strength += 1

      return Math.min(strength, 4)
    }

    function getStrengthColor(strength) {
      switch (strength) {
        case 1:
          return "var(--error-red)"
        case 2:
          return "var(--warning-yellow)"
        case 3:
          return "var(--primary-color)"
        case 4:
          return "var(--success-green)"
        default:
          return "var(--border-color)"
      }
    }

    function getStrengthText(strength) {
      switch (strength) {
        case 1:
          return "Weak password"
        case 2:
          return "Fair password"
        case 3:
          return "Good password"
        case 4:
          return "Strong password"
        default:
          return "Password strength"
      }
    }
  }

  // Form Validation
  const loginForm = document.getElementById("loginForm")
  const adminLoginForm = document.getElementById("adminLoginForm")
  const signupForm = document.getElementById("signupForm")

  if (loginForm) {
    loginForm.addEventListener("submit", function (e) {
      e.preventDefault()

      const email = document.getElementById("email")
      const password = document.getElementById("password")
      const emailError = document.getElementById("emailError")
      const passwordError = document.getElementById("passwordError")

      // Reset errors
      emailError.textContent = ""
      passwordError.textContent = ""

      let isValid = true

      // Email validation
      if (!email.value) {
        emailError.textContent = "Email is required"
        isValid = false
      } else if (!isValidEmail(email.value)) {
        emailError.textContent = "Please enter a valid email address"
        isValid = false
      }

      // Password validation
      if (!password.value) {
        passwordError.textContent = "Password is required"
        isValid = false
      } else if (password.value.length < 8) {
        passwordError.textContent = "Password must be at least 8 characters"
        isValid = false
      }

      if (isValid) {
        // Show loading state
        this.querySelector(".btn-submit").classList.add("loading")

        // Simulate API call
        setTimeout(() => {
          // Redirect to dashboard (in a real app, this would happen after successful login)
          window.location.href = "index.html"
        }, 2000)
      }
    })
  }

  if (adminLoginForm) {
    adminLoginForm.addEventListener("submit", function (e) {
      e.preventDefault();

      const usernameInput = document.getElementById("adminUsername");
      const passwordInput = document.getElementById("adminPassword");
      const usernameError = document.getElementById("usernameError");
      const passwordError = document.getElementById("adminPasswordError");

      // Reset errors
      usernameError.textContent = "";
      passwordError.textContent = "";

      let isValid = true;

      // Username validation
      if (!usernameInput.value) {
        usernameError.textContent = "Username is required";
        isValid = false;
      }

      // Password validation
      if (!passwordInput.value) {
        passwordError.textContent = "Password is required";
        isValid = false;
      }

      if (isValid) {
        // Show loading state by adding the 'loading' class to the submit button
        const btnSubmit = this.querySelector(".btn-submit");
        btnSubmit.classList.add("loading");

        // Prepare login data
        const loginData = {
          username: usernameInput.value,
          password: passwordInput.value,
          role: "ADMIN" // add role if your backend requires it
        };

        // Send AJAX request using Fetch API
        fetch("http://localhost:8080/auth/login/verify", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(loginData)
        })
            .then(response => {
              if (!response.ok) {
                throw new Error("Invalid username or password");
              }
              return response.json();
            })
            .then(data => {
              // If a token is received, store it and redirect to the admin dashboard
              if (data.token) {
                localStorage.setItem("jwtToken", data.token);
                window.location.href = "admin-dashboard.html";
              } else {
                // If authentication fails, display an error message
                usernameError.textContent = data.message || "Login failed";
              }
            })
            .catch(error => {
              passwordError.textContent = error.message;
            })
            .finally(() => {
              // Remove the loading state
              btnSubmit.classList.remove("loading");
            });
      }
    });
  }


// Simple email validation function
  function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  }

  if (signupForm) {
    signupForm.addEventListener("submit", function (e) {
      e.preventDefault();

      // Get form fields
      const userName = document.getElementById("userName");
      const role = document.getElementById("role");
      const email = document.getElementById("signupEmail");
      const password = document.getElementById("signupPassword");
      const termsAgree = document.getElementById("termsAgree");

      // Get error message elements
      const userNameError = document.getElementById("userNameError");
      const roleError = document.getElementById("roleError");
      const emailError = document.getElementById("signupEmailError");
      const passwordError = document.getElementById("signupPasswordError");
      const termsError = document.getElementById("termsError");

      // Reset errors
      userNameError.textContent = "";
      roleError.textContent = "";
      emailError.textContent = "";
      passwordError.textContent = "";
      termsError.textContent = "";

      let isValid = true;

      // Username validation
      if (!userName.value) {
        userNameError.textContent = "Username is required";
        isValid = false;
      }

      // Role validation
      if (!role.value) {
        roleError.textContent = "Role is required";
        isValid = false;
      }

      // Email validation
      if (!email.value) {
        emailError.textContent = "Email is required";
        isValid = false;
      } else if (!isValidEmail(email.value)) {
        emailError.textContent = "Please enter a valid email address";
        isValid = false;
      }

      // Password validation
      if (!password.value) {
        passwordError.textContent = "Password is required";
        isValid = false;
      } else if (password.value.length < 8) {
        passwordError.textContent = "Password must be at least 8 characters";
        isValid = false;
      }

      // Terms agreement validation
      if (!termsAgree.checked) {
        termsError.textContent = "You must agree to the terms and conditions";
        isValid = false;
      }

      if (isValid) {
        // Show loading state
        const btnSubmit = this.querySelector(".btn-submit");
        btnSubmit.classList.add("loading");

        // Prepare signup data object (adjust keys as per your UserDTO)
        const signupData = {
          userName: userName.value,
          role: role.value,
          email: email.value,
          password: password.value
        };

        // Send AJAX request using Fetch API
        fetch("http://localhost:8080/api/user/addUser", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(signupData)
        })
            .then(response => {
              if (!response.ok) {
                throw new Error("Failed to sign up");
              }
              return response.json();
            })
            .then(data => {
              // On success, redirect to the login page (or show a success message)
              window.location.href = "user-login.html";
            })
            .catch(error => {
              // Display error message (you can also choose to display it in a different element)
              termsError.textContent = error.message;
            })
            .finally(() => {
              // Remove loading state
              btnSubmit.classList.remove("loading");
            });
      }
    });
  }



  // Floating elements animation
  const floatingElements = document.querySelectorAll(".float-element")

  floatingElements.forEach((element) => {
    // Add random initial position
    const randomX = Math.random() * 20 - 10
    const randomY = Math.random() * 20 - 10
    element.style.transform = `translate(${randomX}px, ${randomY}px)`
  })

  // Add hover effects to login options
  const loginOptions = document.querySelectorAll(".login-option")

  loginOptions.forEach((option) => {
    option.addEventListener("mouseenter", function () {
      const arrow = this.querySelector(".option-arrow")
      arrow.style.transform = "translateX(5px)"
    })

    option.addEventListener("mouseleave", function () {
      const arrow = this.querySelector(".option-arrow")
      arrow.style.transform = "translateX(0)"
    })
  })

  // Add ripple effect to buttons
  const buttons = document.querySelectorAll(".btn-submit, .social-btn")

  buttons.forEach((button) => {
    button.addEventListener("click", function (e) {
      const rect = this.getBoundingClientRect()
      const x = e.clientX - rect.left
      const y = e.clientY - rect.top

      const ripple = document.createElement("span")
      ripple.classList.add("ripple")
      ripple.style.left = `${x}px`
      ripple.style.top = `${y}px`

      this.appendChild(ripple)

      setTimeout(() => {
        ripple.remove()
      }, 600)
    })
  })

  // Add input focus effects
  const inputs = document.querySelectorAll("input")

  inputs.forEach((input) => {
    input.addEventListener("focus", function () {
      this.parentElement.classList.add("input-focused")
    })

    input.addEventListener("blur", function () {
      this.parentElement.classList.remove("input-focused")
    })
  })

  // Add light ray animation
  const lightRays = document.querySelectorAll(".light-ray")
  lightRays.forEach((ray, index) => {
    ray.style.animationDelay = `${index * 2}s`
  })

  // Add glow pulse animation
  const lightGlows = document.querySelectorAll(".light-glow")
  lightGlows.forEach((glow, index) => {
    glow.style.animationDelay = `${index * 3}s`
  })
})

