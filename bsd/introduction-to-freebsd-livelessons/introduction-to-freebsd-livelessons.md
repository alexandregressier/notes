# Introduction to the FreeBSD Open Source Operating System LiveLessons - Notes

Current FreeBSD version at the time of writing: 13.0-CURRENT

## 1. Introduction to the FreeBSD Community

### 1.1. Understand the FreeBSD Community

FreeBSD is:
- An open-source OS
- Derived from 4.4BSD-Lite (released in 1992 & 1994 by The CSRG at The University of California at Berkeley)
- Picked by a number of different groups, including 386BSD & later FreeBSD 

The CSRG was started by Bill Joy & lead by Kirk McKusick after he left for Sun Microsystems

The different BSDs focuses:
- NetBSD: continuing the research in the same way as the original BSD & portability
- OpenBSD: security
- FreeBSD: make BSD generally available for the people that run commidity PC hardware

The BSDs are part of the very few open-source projects still active after 20 years+

Fact: 90% of the projects on GitHub & the like are "dark" (last change is from >1 year):
-> One leader runs it for some time & then loses interest

1 study tried to understand how the long-lived ones succeeded
- Shown that there must be some methodology to turnover the leadership (like FreeBSD)

FreeBSD is not as popular as Linux mostly b/c the latter focused more on the desktop & the former on the server

FreeBSD provides core Internet support b/c it runs on:
- Root names servers 
- Major web hosts & search engines (_e.g._, Yahoo)
- Routing infrastructure (for more generalized routing, competing w/ Cisco)

Linux is for your small IoT devices whereas FreeBSD is for core routers that deals w/ large volume of data

Foundation for major commercial OSes:
- ISP/Server platform
  - Netflix (using 1/3 of all the domestic internet): routing & CDN
  - Yahoo: entire infrastructure
  - WhatsApp: 600M users served by 1200 BSD machines
  - BBC
  - Internet Software Consortium (ISC): responsible for nameservers/root servers
- Appliance/Embedded OS
  - Juniper Networks: up w/ Cisco, built huge backbone routers
  - NetApp: storage systems in the cloud
  - Dell EMC Isilon: huge storage appliances
  - Panasas: specialized systems
  - Apple's Darwin (base OS of macOS)

FreeBSD project structure:
- Central source code repository (using Apache Subversion)
- Subversion feeds to Git (b/c devs are more familiar w/ the latter)
- 40+ mirrors for all VCSes

Rings:
- Users: no development involved
- Developers (a.k.a. Contributors): make PRs
- Comitters: can update Subversion on central site

Linux has choice fatigue w/ distributions (+many debates about changes in each community)

Conversely, "FreeBSD" does not refer to the "FreeBSD kernel" but the **whole project**:

Core system is composed of:
- 100 libraries
- 775 utilities
- The symmetric multithreaded kernel (SMP: Symmetric multiprocessing)
  - Supported architectures: Intel/AMD 32/64-bit, ARM, MIPS, PowerPC, SPARC...
  - https://www.freebsd.org/platforms/

All other software is **maintained** in the _ports collection_ (25K+ packages)
- Ensure that it runs smoothly
- Tweaks
- Compiled on a regular basis

Releases:
- Since FreeBSD is re-appropriated everywhere, releases are maintained for at least 5 years
- Development take place on the main trunk (`-CURRENT` release): new features
- Stable releases on branches (`-STABLE` releases)
- Most bugs/security issues are discovered on `-CURRENT`, but it might also happen on `-STABLE`
- `-STABLE` branches are cut every 2-3 years, & `-CURRENT` becomes the new `.0-STABLE`
- Every ~6 months, a new `-RELEASE` is done on the `-STABLE` branch
