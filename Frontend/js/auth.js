// Colorful Auth Pages JavaScript
document.addEventListener('DOMContentLoaded', function() {
    // Theme Toggle
    const themeToggle = document.getElementById('theme-toggle');
    const htmlElement = document.documentElement;
    
    // Check for saved theme preference or use system preference
    const savedTheme = localStorage.getItem('theme');
    const systemPrefersDark = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
    
    // Set initial theme
    if (savedTheme === 'dark' || (!savedTheme && systemPrefersDark)) {
      htmlElement.classList.add('dark-mode');
    } else {
      htmlElement.classList.add('light-mode');
    }
    
    // Toggle theme when button is clicked
    if (themeToggle) {
      themeToggle.addEventListener('click', function() {
        if (htmlElement.classList.contains('dark-mode')) {
          htmlElement.classList.remove('dark-mode');
          htmlElement.classList.add('light-mode');
          localStorage.setItem('theme', 'light');
        } else {
          htmlElement.classList.remove('light-mode');
          htmlElement.classList.add('dark-mode');
          localStorage.setItem('theme', 'dark');
        }
      });
    }
    
    // Password toggle
    const togglePasswordButtons = document.querySelectorAll('.toggle-password');
    togglePasswordButtons.forEach(button => {
      button.addEventListener('click', function() {
        const input = this.previousElementSibling;
        const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
        input.setAttribute('type', type);
        this.classList.toggle('password-visible');
      });
    });
    
    // Password strength meter
    const passwordInput = document.getElementById('password');
    if (passwordInput) {
      passwordInput.addEventListener('input', function() {
        updatePasswordStrength(this.value);
      });
    }
    
    // Form validation
    const userLoginForm = document.getElementById('user-login-form');
    if (userLoginForm) {
      userLoginForm.addEventListener('submit', function(e) {
        e.preventDefault();
        validateLoginForm(this);
      });
    }
    
    const adminLoginForm = document.getElementById('admin-login-form');
    if (adminLoginForm) {
      adminLoginForm.addEventListener('submit', function(e) {
        e.preventDefault();
        validateLoginForm(this);
      });
    }
    
    const signupForm = document.getElementById('signup-form');
    if (signupForm) {
      signupForm.addEventListener('submit', function(e) {
        e.preventDefault();
        validateSignUpForm();
      });
    }
    
    // Add animation to floating elements
    animateFloatingElements();
    
    // Password strength meter
    function updatePasswordStrength(password) {
      const strengthSegments = document.querySelectorAll('.strength-segment');
      const strengthText = document.querySelector('.strength-text');
      
      if (!strengthSegments.length || !strengthText) return;
      
      // Reset all segments
      strengthSegments.forEach(segment => {
        segment.className = 'strength-segment';
      });
      
      // Check password strength
      let strength = 0;
      
      if (password.length >= 8) strength++;
      if (password.match(/[a-z]/) && password.match(/[A-Z]/)) strength++;
      if (password.match(/\d/)) strength++;
      if (password.match(/[^a-zA-Z\d]/)) strength++;
      
      // Update UI based on strength
      if (password.length === 0) {
        strengthText.textContent = 'Password strength';
      } else if (strength < 2) {
        strengthText.textContent = 'Weak';
        for (let i = 0; i < 1; i++) {
          strengthSegments[i].classList.add('weak');
        }
      } else if (strength < 4) {
        strengthText.textContent = 'Medium';
        for (let i = 0; i < 3; i++) {
          strengthSegments[i].classList.add('medium');
        }
      } else {
        strengthText.textContent = 'Strong';
        for (let i = 0; i < 4; i++) {
          strengthSegments[i].classList.add('strong');
        }
      }
    }
    
    // Form validation
    function validateLoginForm(form) {
      let isValid = true;
      const emailInput = form.querySelector('input[type="email"]');
      const usernameInput = form.querySelector('input[name="username"]');
      const passwordInput = form.querySelector('input[type="password"]');
      
      if (emailInput && !isValidEmail(emailInput.value)) {
        showError(emailInput, 'Please enter a valid email address');
        isValid = false;
      }
      
      if (usernameInput && usernameInput.value.length < 3) {
        showError(usernameInput, 'Username must be at least 3 characters');
        isValid = false;
      }
      
      if (passwordInput.value.length < 6) {
        showError(passwordInput, 'Password must be at least 6 characters');
        isValid = false;
      }
      
      if (isValid) {
        // For demo purposes, simulate successful login
        showSuccess(form);
      }
      
      return isValid;
    }
    
    function validateSignUpForm() {
      let isValid = true;
      const firstName = document.getElementById('first-name');
      const lastName = document.getElementById('last-name');
      const email = document.getElementById('email');
      const password = document.getElementById('password');
      const confirmPassword = document.getElementById('confirm-password');
      const terms = document.getElementById('terms');
      
      if (!firstName.value) {
        showError(firstName, 'First name is required');
        isValid = false;
      }
      
      if (!lastName.value) {
        showError(lastName, 'Last name is required');
        isValid = false;
      }
      
      if (!isValidEmail(email.value)) {
        showError(email, 'Please enter a valid email address');
        isValid = false;
      }
      
      if (password.value.length < 8) {
        showError(password, 'Password must be at least 8 characters');
        isValid = false;
      }
      
      if (password.value !== confirmPassword.value) {
        showError(confirmPassword, 'Passwords do not match');
        isValid = false;
      }
      
      if (!terms.checked) {
        showError(terms.parentElement, 'You must agree to the Terms of Service');
        isValid = false;
      }
      
      if (isValid) {
        // For demo purposes, simulate successful signup
        showSuccess(document.getElementById('signup-form'));
      }
      
      return isValid;
    }
    
    function isValidEmail(email) {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return emailRegex.test(email);
    }
    
    function showError(input, message) {
      // Remove any existing error
      const existingError = input.parentElement.querySelector('.error-message');
      if (existingError) {
        existingError.remove();
      }
      
      // Add error class to input
      input.classList.add('error');
      
      // Create error message
      const errorDiv = document.createElement('div');
      errorDiv.className = 'error-message';
      errorDiv.textContent = message;
      
      // Insert error after input wrapper
      if (input.classList.contains('checkmark')) {
        input.parentElement.parentElement.appendChild(errorDiv);
      } else {
        input.parentElement.appendChild(errorDiv);
      }
      
      // Add error styles
      errorDiv.style.color = '#ff4d4f';
      errorDiv.style.fontSize = '0.8rem';
      errorDiv.style.marginTop = '0.5rem';
      input.style.borderColor = '#ff4d4f';
      
      // Remove error after 3 seconds
      setTimeout(() => {
        errorDiv.remove();
        input.classList.remove('error');
        input.style.borderColor = '';
      }, 3000);
    }
    
    function showSuccess(form) {
      // Create success message
      const successDiv = document.createElement('div');
      successDiv.className = 'success-message';
      successDiv.textContent = 'Success! Redirecting...';
      
      // Add success styles
      successDiv.style.color = '#52c41a';
      successDiv.style.fontSize = '1rem';
      successDiv.style.textAlign = 'center';
      successDiv.style.padding = '1rem';
      successDiv.style.marginTop = '1rem';
      successDiv.style.backgroundColor = 'rgba(82, 196, 26, 0.1)';
      successDiv.style.borderRadius = '8px';
      
      // Insert success message
      form.appendChild(successDiv);
      
      // Disable form
      const inputs = form.querySelectorAll('input, button');
      inputs.forEach(input => {
        input.disabled = true;
      });
      
      // Simulate redirect after 2 seconds
      setTimeout(() => {
        window.location.href = 'index.html';
      }, 2000);
    }
    
    // Animate floating elements
    function animateFloatingElements() {
      const floatingElements = document.querySelectorAll('.floating-element');
      
      floatingElements.forEach(element => {
        // Add random movement
        setInterval(() => {
          const randomX = Math.random() * 20 - 10; // -10 to 10
          const randomY = Math.random() * 20 - 10; // -10 to 10
          
          element.style.transform = `translate(${randomX}px, ${randomY}px)`;
        }, 3000);
      });
    }
  });