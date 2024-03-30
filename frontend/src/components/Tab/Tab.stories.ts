import type { Meta, StoryObj } from '@storybook/react';

import Tab from './Tab';

const meta = {
  title: 'Common/Tab',
  component: Tab,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    tabs: {
      description: 'label과 컴포넌트 객체 배열을 넘겨줄 수 있습니다',
    },
  },
} satisfies Meta<typeof Tab>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    tabs: [
      {
        label: '주식목록',
        component: undefined,
      },
      {
        label: '내 잔고',
        component: undefined,
      },
    ],
  },
};
