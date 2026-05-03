package com.summer.cleaner.robot;

import com.summer.cleaner.function.impl.MoveImpl;
import com.summer.cleaner.function.impl.StartImpl;
import com.summer.cleaner.function.impl.TurnImpl;
import org.junit.jupiter.api.Test;

class CleanerFunctionalImplTest {

  @Test
  void firstTest() {
    CleanerFunctionalDI cleanerFunctionalImpl = new CleanerFunctionalDI(
        new MoveImpl(),
        new TurnImpl(),
        (cleaner, argument) -> CleanerFunctionalStaticImpl.set((CleanerImpl) cleaner, argument),
        new StartImpl(),
        CleanerFunctionalStaticImpl::stop_2);
  }
}