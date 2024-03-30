import type { Meta, StoryObj } from '@storybook/react';

import ProgressBar from './ProgressBar';

const meta = {
  title: 'Common/ProgressBar',
  component: ProgressBar,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    barGage: {
      description: `입력한 숫자의 퍼센트만큼 바 게이지가 차며 100이 넘으면 더이상 차지 않습니다.`,
    },
  },
} satisfies Meta<typeof ProgressBar>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    barGage: 50,
  },
};
