// Book Now Page JavaScript
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
    
    // Initialize AOS
    initAOS();
    
    // Initialize split text animation
    initSplitText();
    
    // Form validation
    const bookingForm = document.getElementById('bookingForm');
    
    if (bookingForm) {
      bookingForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // Simple validation
        let valid = true;
        const requiredFields = bookingForm.querySelectorAll('[required]');
        
        requiredFields.forEach(field => {
          if (!field.value.trim()) {
            valid = false;
            field.style.borderColor = 'red';
            
            // Add error message if it doesn't exist
            let errorMessage = field.parentNode.querySelector('.error-message');
            if (!errorMessage) {
              errorMessage = document.createElement('div');
              errorMessage.className = 'error-message';
              errorMessage.textContent = 'This field is required';
              errorMessage.style.color = 'red';
              errorMessage.style.fontSize = '0.8rem';
              errorMessage.style.marginTop = '0.25rem';
              field.parentNode.appendChild(errorMessage);
            }
          } else {
            field.style.borderColor = 'var(--border-color)';
            
            // Remove error message if it exists
            const errorMessage = field.parentNode.querySelector('.error-message');
            if (errorMessage) {
              field.parentNode.removeChild(errorMessage);
            }
          }
        });
        
        // Validate email format
        const emailField = document.getElementById('email');
        if (emailField && emailField.value.trim() && !isValidEmail(emailField.value)) {
          valid = false;
          emailField.style.borderColor = 'red';
          
          // Add error message if it doesn't exist
          let errorMessage = emailField.parentNode.querySelector('.error-message');
          if (!errorMessage) {
            errorMessage = document.createElement('div');
            errorMessage.className = 'error-message';
            errorMessage.textContent = 'Please enter a valid email address';
            errorMessage.style.color = 'red';
            errorMessage.style.fontSize = '0.8rem';
            errorMessage.style.marginTop = '0.25rem';
            emailField.parentNode.appendChild(errorMessage);
          } else {
            errorMessage.textContent = 'Please enter a valid email address';
          }
        }
        
        // Validate card number format
        const cardNumberField = document.getElementById('card-number');
        if (cardNumberField && cardNumberField.value.trim() && !isValidCardNumber(cardNumberField.value)) {
          valid = false;
          cardNumberField.style.borderColor = 'red';
          
          // Add error message if it doesn't exist
          let errorMessage = cardNumberField.parentNode.querySelector('.error-message');
          if (!errorMessage) {
            errorMessage = document.createElement('div');
            errorMessage.className = 'error-message';
            errorMessage.textContent = 'Please enter a valid card number';
            errorMessage.style.color = 'red';
            errorMessage.style.fontSize = '0.8rem';
            errorMessage.style.marginTop = '0.25rem';
            cardNumberField.parentNode.appendChild(errorMessage);
          } else {
            errorMessage.textContent = 'Please enter a valid card number';
          }
        }
        
        // Validate expiry date format
        const expiryDateField = document.getElementById('expiry-date');
        if (expiryDateField && expiryDateField.value.trim() && !isValidExpiryDate(expiryDateField.value)) {
          valid = false;
          expiryDateField.style.borderColor = 'red';
          
          // Add error message if it doesn't exist
          let errorMessage = expiryDateField.parentNode.querySelector('.error-message');
          if (!errorMessage) {
            errorMessage = document.createElement('div');
            errorMessage.className = 'error-message';
            errorMessage.textContent = 'Please enter a valid expiry date (MM/YY)';
            errorMessage.style.color = 'red';
            errorMessage.style.fontSize = '0.8rem';
            errorMessage.style.marginTop = '0.25rem';
            expiryDateField.parentNode.appendChild(errorMessage);
          } else {
            errorMessage.textContent = 'Please enter a valid expiry date (MM/YY)';
          }
        }
        
        // Validate CVV format
        const cvvField = document.getElementById('cvv');
        if (cvvField && cvvField.value.trim() && !isValidCVV(cvvField.value)) {
          valid = false;
          cvvField.style.borderColor = 'red';
          
          // Add error message if it doesn't exist
          let errorMessage = cvvField.parentNode.querySelector('.error-message');
          if (!errorMessage) {
            errorMessage = document.createElement('div');
            errorMessage.className = 'error-message';
            errorMessage.textContent = 'Please enter a valid CVV';
            errorMessage.style.color = 'red';
            errorMessage.style.fontSize = '0.8rem';
            errorMessage.style.marginTop = '0.25rem';
            cvvField.parentNode.appendChild(errorMessage);
          } else {
            errorMessage.textContent = 'Please enter a valid CVV';
          }
        }
        
        if (valid) {
          // Simulate form submission
          const submitBtn = bookingForm.querySelector('button[type="submit"]');
          const originalText = submitBtn.textContent;
          
          submitBtn.disabled = true;
          submitBtn.textContent = 'Processing...';
          
          setTimeout(() => {
            // Redirect to confirmation page (in a real application)
            alert('Booking successful! You will be redirected to the confirmation page.');
            
            // Reset form
            bookingForm.reset();
            
            // Reset button
            submitBtn.disabled = false;
            submitBtn.textContent = originalText;
          }, 2000);
        } else {
          // Scroll to first error
          const firstError = bookingForm.querySelector('[style*="border-color: red"]');
          if (firstError) {
            firstError.scrollIntoView({ behavior: 'smooth', block: 'center' });
            firstError.focus();
          }
        }
      });
    }
    
    // Payment method toggle
    const paymentMethods = document.querySelectorAll('input[name="payment-method"]');
    const creditCardForm = document.getElementById('credit-card-form');
    
    paymentMethods.forEach(method => {
      method.addEventListener('change', function() {
        if (this.value === 'credit-card') {
          creditCardForm.style.display = 'block';
        } else {
          creditCardForm.style.display = 'none';
        }
      });
    });
    
    // Format card number with spaces
    const cardNumberInput = document.getElementById('card-number');
    if (cardNumberInput) {
      cardNumberInput.addEventListener('input', function() {
        let value = this.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
        let formattedValue = '';
        
        for (let i = 0; i < value.length; i++) {
          if (i > 0 && i % 4 === 0) {
            formattedValue += ' ';
          }
          formattedValue += value[i];
        }
        
        this.value = formattedValue;
      });
    }
    
    // Format expiry date
    const expiryDateInput = document.getElementById('expiry-date');
    if (expiryDateInput) {
      expiryDateInput.addEventListener('input', function() {
        let value = this.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
        
        if (value.length > 2) {
          this.value = value.substring(0, 2) + '/' + value.substring(2, 4);
        } else {
          this.value = value;
        }
      });
    }
    
    // Update booking summary based on form selections
    const destinationSelect = document.getElementById('destination');
    const departureDate = document.getElementById('departure-date');
    const returnDate = document.getElementById('return-date');
    const adultsSelect = document.getElementById('adults');
    const childrenSelect = document.getElementById('children');
    const roomTypeSelect = document.getElementById('room-type');
    
    // Update summary when form fields change
    if (destinationSelect && departureDate && returnDate && adultsSelect && childrenSelect && roomTypeSelect) {
      const updateSummary = function() {
        // Update destination
        const selectedDestination = destinationSelect.options[destinationSelect.selectedIndex].text;
        const summaryDestinationTitle = document.querySelector('.destination-details h3');
        if (summaryDestinationTitle) {
          summaryDestinationTitle.textContent = selectedDestination.split(',')[0];
        }
        
        // Update dates
        if (departureDate.value && returnDate.value) {
          const formattedDepartureDate = formatDate(departureDate.value);
          const formattedReturnDate = formatDate(returnDate.value);
          const datesValue = document.querySelector('.summary-item:nth-child(1) .summary-value');
          if (datesValue) {
            datesValue.textContent = `${formattedDepartureDate} - ${formattedReturnDate}`;
          }
          
          // Calculate nights
          const start = new Date(departureDate.value);
          const end = new Date(returnDate.value);
          const nights = Math.round((end - start) / (1000 * 60 * 60 * 24));
          const durationValue = document.querySelector('.summary-item:nth-child(4) .summary-value');
          if (durationValue && nights > 0) {
            durationValue.textContent = `${nights} Nights`;
          }
        }
        
        // Update guests
        const adults = adultsSelect.value;
        const children = childrenSelect.value;
        const guestsValue = document.querySelector('.summary-item:nth-child(2) .summary-value');
        if (guestsValue) {
          let guestsText = `${adults} Adult${adults > 1 ? 's' : ''}`;
          if (parseInt(children) > 0) {
            guestsText += `, ${children} Child${children > 1 ? 'ren' : ''}`;
          }
          guestsValue.textContent = guestsText;
        }
        
        // Update room type
        const selectedRoomType = roomTypeSelect.options[roomTypeSelect.selectedIndex].text;
        const roomValue = document.querySelector('.summary-item:nth-child(3) .summary-value');
        if (roomValue) {
          roomValue.textContent = selectedRoomType;
        }
      };
      
      // Format date to MMM DD, YYYY
      function formatDate(dateString) {
        const date = new Date(dateString);
        const options = { year: 'numeric', month: 'short', day: 'numeric' };
        return date.toLocaleDateString('en-US', options);
      }
      
      // Add event listeners to form fields
      destinationSelect.addEventListener('change', updateSummary);
      departureDate.addEventListener('change', updateSummary);
      returnDate.addEventListener('change', updateSummary);
      adultsSelect.addEventListener('change', updateSummary);
      childrenSelect.addEventListener('change', updateSummary);
      roomTypeSelect.addEventListener('change', updateSummary);
      
      // Initialize summary with default values
      updateSummary();
    }
    
    // Validation helper functions
    function isValidEmail(email) {
      const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(String(email).toLowerCase());
    }
    
    function isValidCardNumber(cardNumber) {
      const re = /^[\d\s]{13,19}$/;
      return re.test(cardNumber.trim());
    }
    
    function isValidExpiryDate(expiryDate) {
      const re = /^(0[1-9]|1[0-2])\/([0-9]{2})$/;
      if (!re.test(expiryDate.trim())) {
        return false;
      }
      
      const [month, year] = expiryDate.split('/');
      const currentDate = new Date();
      const currentYear = currentDate.getFullYear() % 100;
      const currentMonth = currentDate.getMonth() + 1;
      
      const expYear = parseInt(year);
      const expMonth = parseInt(month);
      
      if (expYear < currentYear || (expYear === currentYear && expMonth < currentMonth)) {
        return false;
      }
      
      return true;
    }
    
    function isValidCVV(cvv) {
      const re = /^[0-9]{3,4}$/;
      return re.test(cvv.trim());
    }
    
    // Initialize AOS (Animate on Scroll)
    function initAOS() {
      const animatedElements = document.querySelectorAll('[data-aos]');
      
      const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            entry.target.classList.add('aos-animate');
          } else {
            // Uncomment to enable re-animation when scrolling back up
            // entry.target.classList.remove('aos-animate');
          }
        });
      }, {
        threshold: 0.1
      });
      
      animatedElements.forEach(element => {
        observer.observe(element);
        
        // Add delay if specified
        const delay = element.getAttribute('data-aos-delay');
        if (delay) {
          element.style.transitionDelay = `${delay}ms`;
        }
      });
    }
    
    // Split text animation
    function initSplitText() {
      const splitTextElements = document.querySelectorAll('.split-text');
      
      splitTextElements.forEach(element => {
        const text = element.textContent;
        element.textContent = '';
        
        for (let i = 0; i < text.length; i++) {
          const char = document.createElement('span');
          char.className = 'char';
          char.style.transitionDelay = `${i * 30}ms`;
          char.textContent = text[i] === ' ' ? '\u00A0' : text[i];
          element.appendChild(char);
        }
        
        // Trigger animation after a short delay
        setTimeout(() => {
          const chars = element.querySelectorAll('.char');
          chars.forEach(char => {
            char.style.opacity = 1;
            char.style.transform = 'translateY(0)';
          });
        }, 500);
      });
    }
    
    // Set current year in footer
    const currentYearElement = document.getElementById('currentYear');
    if (currentYearElement) {
      currentYearElement.textContent = new Date().getFullYear();
    }
  });