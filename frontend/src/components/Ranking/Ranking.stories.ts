import type { Meta, StoryObj } from '@storybook/react';

import Ranking from './Ranking';

const meta = {
  title: 'Common/Ranking',
  component: Ranking,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description: 'HIGHLIGHT는 자신의 순위와 1, 2, 3등에 적용',
    },
    rank: {
      description: '순위 저장',
    },
    name: {
      description: '이름 저장',
    },
    chocochip: {
      description: '초코칩 저장',
    },
  },
} satisfies Meta<typeof Ranking>;

export default meta;
type Story = StoryObj<typeof meta>;

export const HighlightTopRank: Story = {
  args: {
    mode: 'HIGHLIGHT',
    rank: 1,
    name: '김라딘',
    chocochip: 999999990,
  },
};

export const Highlight: Story = {
  args: {
    mode: 'HIGHLIGHT',
    rank: 472,
    name: '윤지니',
    chocochip: 21000970,
  },
};

export const General: Story = {
  args: {
    mode: 'GENERAL',
    rank: 10,
    name: '김지니',
    chocochip: 990999990,
  },
};
