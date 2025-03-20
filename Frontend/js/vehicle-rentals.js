// Vehicle Rentals Page JavaScript
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
  } else {
    htmlElement.classList.add("light-mode")
  }

  // Toggle theme when button is clicked
  if (themeToggle) {
    themeToggle.addEventListener("click", () => {
      if (htmlElement.classList.contains("dark-mode")) {
        htmlElement.classList.remove("dark-mode")
        htmlElement.classList.add("light-mode")
        localStorage.setItem("theme", "light")
      } else {
        htmlElement.classList.remove("light-mode")
        htmlElement.classList.add("dark-mode")
        localStorage.setItem("theme", "dark")
      }
    })
  }

  // Mobile Menu Toggle
  const mobileMenuToggle = document.querySelector(".mobile-menu-toggle")
  const mainNav = document.querySelector(".main-nav")

  if (mobileMenuToggle && mainNav) {
    mobileMenuToggle.addEventListener("click", function () {
      this.classList.toggle("active")
      mainNav.classList.toggle("active")

      if (this.classList.contains("active")) {
        document.body.style.overflow = "hidden"
      } else {
        document.body.style.overflow = ""
      }
    })
  }

  // Back to Top Button
  const backToTopButton = document.getElementById("back-to-top")

  if (backToTopButton) {
    window.addEventListener("scroll", () => {
      if (window.pageYOffset > 300) {
        backToTopButton.classList.add("visible")
      } else {
        backToTopButton.classList.remove("visible")
      }
    })

    backToTopButton.addEventListener("click", () => {
      window.scrollTo({
        top: 0,
        behavior: "smooth",
      })
    })
  }

  // Initialize AOS (Animate on Scroll)
  initAOS()

  // Initialize Sliders
  initSliders()

  // Initialize FAQ Accordion
  initFAQ()

  // Initialize Form Validation
  initFormValidation()

  // Initialize Vehicle Filters
  initVehicleFilters()

  // Initialize Date Pickers
  initDatePickers()

  // Animate on Scroll
  function initAOS() {
    const animatedElements = document.querySelectorAll("[data-aos]")

    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add("aos-animate")
          } else {
            // Uncomment to enable re-animation when scrolling back up
            // entry.target.classList.remove('aos-animate');
          }
        })
      },
      {
        threshold: 0.1,
      },
    )

    animatedElements.forEach((element) => {
      observer.observe(element)

      // Add delay if specified
      const delay = element.getAttribute("data-aos-delay")
      if (delay) {
        element.style.transitionDelay = `${delay}ms`
      }
    })
  }

  // Sliders (Featured Vehicles & Reviews)
  function initSliders() {
    // Featured Vehicles Slider
    initSlider(".featured-vehicles-container", ".slider-controls")

    // Reviews Slider
    initSlider(".reviews-container", ".reviews-slider .slider-controls")

    function initSlider(containerSelector, controlsSelector) {
      const container = document.querySelector(containerSelector)
      if (!container) return

      const controls = document.querySelector(controlsSelector)
      if (!controls) return

      const prevBtn = controls.querySelector(".prev")
      const nextBtn = controls.querySelector(".next")
      const dots = controls.querySelectorAll(".dot")

      let currentSlide = 0
      const slides = container.children
      const slideCount = slides.length

      // Set initial state
      updateSlider()

      // Event listeners
      if (prevBtn) {
        prevBtn.addEventListener("click", () => {
          currentSlide = (currentSlide - 1 + slideCount) % slideCount
          updateSlider()
        })
      }

      if (nextBtn) {
        nextBtn.addEventListener("click", () => {
          currentSlide = (currentSlide + 1) % slideCount
          updateSlider()
        })
      }

      dots.forEach((dot, index) => {
        dot.addEventListener("click", () => {
          currentSlide = index
          updateSlider()
        })
      })

      // Auto slide every 5 seconds
      let autoSlideInterval = setInterval(autoSlide, 5000)

      // Pause auto slide on hover
      container.addEventListener("mouseenter", () => {
        clearInterval(autoSlideInterval)
      })

      container.addEventListener("mouseleave", () => {
        autoSlideInterval = setInterval(autoSlide, 5000)
      })

      function autoSlide() {
        currentSlide = (currentSlide + 1) % slideCount
        updateSlider()
      }

      function updateSlider() {
        // Update slides
        for (let i = 0; i < slideCount; i++) {
          if (i === currentSlide) {
            slides[i].style.display = "block"
          } else {
            slides[i].style.display = "none"
          }
        }

        // Update dots
        dots.forEach((dot, index) => {
          if (index === currentSlide) {
            dot.classList.add("active")
          } else {
            dot.classList.remove("active")
          }
        })
      }
    }
  }

  // FAQ Accordion
  function initFAQ() {
    const faqItems = document.querySelectorAll(".faq-item")

    faqItems.forEach((item) => {
      const question = item.querySelector(".faq-question")
      const answer = item.querySelector(".faq-answer")
      const toggleBtn = item.querySelector(".toggle-btn")

      // Initially hide all answers except the first one
      if (item !== faqItems[0]) {
        answer.style.display = "none"
      } else {
        item.classList.add("active")
        toggleBtn.innerHTML = `
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
          <line x1="5" y1="12" x2="19" y2="12"></line>
        </svg>
      `
      }

      question.addEventListener("click", () => {
        const isActive = item.classList.contains("active")

        // Close all items
        faqItems.forEach((faqItem) => {
          faqItem.classList.remove("active")
          faqItem.querySelector(".faq-answer").style.display = "none"
          faqItem.querySelector(".toggle-btn").innerHTML = `
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <line x1="12" y1="5" x2="12" y2="19"></line>
            <line x1="5" y1="12" x2="19" y2="12"></line>
          </svg>
        `
        })

        // Open current item if it was closed
        if (!isActive) {
          item.classList.add("active")
          answer.style.display = "block"
          toggleBtn.innerHTML = `
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <line x1="5" y1="12" x2="19" y2="12"></line>
          </svg>
        `
        }
      })
    })
  }

  // Form Validation
  function initFormValidation() {
    const bookingForm = document.querySelector(".booking-form")

    if (bookingForm) {
      bookingForm.addEventListener("submit", (e) => {
        e.preventDefault()

        let isValid = true
        const pickupLocation = document.getElementById("pickup-location")
        const pickupDate = document.getElementById("pickup-date")
        const pickupTime = document.getElementById("pickup-time")
        const dropoffDate = document.getElementById("dropoff-date")
        const dropoffTime = document.getElementById("dropoff-time")

        // Simple validation
        if (!pickupLocation.value) {
          isValid = false
          pickupLocation.classList.add("error")
        } else {
          pickupLocation.classList.remove("error")
        }

        if (!pickupDate.value) {
          isValid = false
          pickupDate.classList.add("error")
        } else {
          pickupDate.classList.remove("error")
        }

        if (!pickupTime.value) {
          isValid = false
          pickupTime.classList.add("error")
        } else {
          pickupTime.classList.remove("error")
        }

        if (!dropoffDate.value) {
          isValid = false
          dropoffDate.classList.add("error")
        } else {
          dropoffDate.classList.remove("error")
        }

        if (!dropoffTime.value) {
          isValid = false
          dropoffTime.classList.add("error")
        } else {
          dropoffTime.classList.remove("error")
        }

        // Date validation
        if (pickupDate.value && dropoffDate.value) {
          const pickup = new Date(`${pickupDate.value}T${pickupTime.value}`)
          const dropoff = new Date(`${dropoffDate.value}T${dropoffTime.value}`)

          if (dropoff <= pickup) {
            isValid = false
            dropoffDate.classList.add("error")
            alert("Drop-off date must be after pickup date")
          }
        }

        if (isValid) {
          // In a real app, this would submit the form or redirect to search results
          alert("Searching for available vehicles...")

          // Redirect to search results (simulated)
          window.location.href = "#all-vehicles"
        }
      })
    }
  }

  // Vehicle Filters
  function initVehicleFilters() {
    const categoryFilter = document.getElementById("category-filter")
    const priceFilter = document.getElementById("price-filter")
    const passengersFilter = document.getElementById("passengers-filter")
    const sortFilter = document.getElementById("sort-filter")
    const vehicleCards = document.querySelectorAll(".vehicle-card")
    const loadMoreBtn = document.querySelector(".btn-load-more")

    // Apply filters when any filter changes
    const filters = [categoryFilter, priceFilter, passengersFilter, sortFilter]

    filters.forEach((filter) => {
      if (filter) {
        filter.addEventListener("change", applyFilters)
      }
    })

    // Load more vehicles
    if (loadMoreBtn) {
      loadMoreBtn.addEventListener("click", () => {
        // In a real app, this would load more vehicles from the server
        alert("Loading more vehicles...")
      })
    }

    function applyFilters() {
      // In a real app, this would filter the vehicles based on the selected criteria
      // For demo purposes, we'll just log the selected values

      const selectedFilters = {
        category: categoryFilter ? categoryFilter.value : "",
        price: priceFilter ? priceFilter.value : "",
        passengers: passengersFilter ? passengersFilter.value : "",
        sort: sortFilter ? sortFilter.value : "",
      }

      console.log("Filters applied:", selectedFilters)

      // Simulate filtering (in a real app, this would filter the actual data)
      vehicleCards.forEach((card) => {
        // Add a slight delay for visual effect
        setTimeout(() => {
          card.style.opacity = "0.7"
          setTimeout(() => {
            card.style.opacity = "1"
          }, 300)
        }, Math.random() * 300)
      })
    }
  }

  // Date Pickers
  function initDatePickers() {
    const pickupDate = document.getElementById("pickup-date")
    const dropoffDate = document.getElementById("dropoff-date")

    if (pickupDate && dropoffDate) {
      // Set min date to today
      const today = new Date()
      const tomorrow = new Date(today)
      tomorrow.setDate(tomorrow.getDate() + 1)

      const formatDate = (date) => {
        const year = date.getFullYear()
        const month = String(date.getMonth() + 1).padStart(2, "0")
        const day = String(date.getDate()).padStart(2, "0")
        return `${year}-${month}-${day}`
      }

      pickupDate.min = formatDate(today)
      dropoffDate.min = formatDate(tomorrow)

      // Update dropoff min date when pickup date changes
      pickupDate.addEventListener("change", function () {
        if (this.value) {
          const newMinDate = new Date(this.value)
          newMinDate.setDate(newMinDate.getDate() + 1)
          dropoffDate.min = formatDate(newMinDate)

          // If current dropoff date is before new min date, update it
          if (dropoffDate.value && new Date(dropoffDate.value) < newMinDate) {
            dropoffDate.value = formatDate(newMinDate)
          }
        }
      })

      // Set default dates (today and tomorrow)
      pickupDate.value = formatDate(today)
      dropoffDate.value = formatDate(tomorrow)
    }
  }
})

