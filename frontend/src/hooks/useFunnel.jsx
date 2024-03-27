'use client';

import { useState } from 'react';

export function useFunnel(initialStep) {
  const [step, setStep] = useState(initialStep);

  const Step = (props) => {
    return <>{props.children}</>;
  };

  const Funnel = ({ children }) => {
    const targetStep = children.find(
      (childStep) => childStep.props.name === step,
    );
    return { ...targetStep };
  };

  Funnel.Step = Step;

  return [Funnel, setStep];
}
