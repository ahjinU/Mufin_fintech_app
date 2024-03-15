'use client';

import { useRef, useState } from 'react';

interface Tab {
  label: string;
  component: React.ReactElement | undefined;
}

interface TabProps {
  tabs: Tab[];
}

export default function Tab({ tabs }: TabProps) {
  const [activeIndex, setActiveIndex] = useState<number>(0);
  const [activeTab, setActiveTab] = useState<React.ReactElement | undefined>(
    tabs[0].component,
  );

  const handleClick = (index: number) => {
    setActiveIndex(index);
    setActiveTab(tabs[index].component);
  };

  return (
    <div className="w-full h-full">
      <div className="w-full h-[5.0rem] flex flex-row">
        {tabs.map(({ label }, index) => {
          return (
            <button
              className={`w-full border-b-[0.2rem] ${
                activeIndex === index
                  ? 'border-custom-purple'
                  : 'border-custom-light-gray'
              }`}
              onClick={() => handleClick(index)}
              key={label}
            >
              <p
                className={`${
                  activeIndex === index
                    ? 'text-custom-purple'
                    : 'text-custom-medium-gray'
                } text-[1.6rem]`}
              >
                {label}
              </p>
            </button>
          );
        })}
      </div>
      <div>{activeTab}</div>
    </div>
  );
}
